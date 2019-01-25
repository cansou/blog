package com.blog.cloud.config;

import com.blog.cloud.domain.request.*;
import com.blog.cloud.domain.response.*;
import com.blog.cloud.domain.shared.ChatRoomDescription;
import com.blog.cloud.domain.shared.SyncKey;
import com.blog.cloud.domain.shared.Token;
import com.blog.cloud.domain.shared.VerifyUser;
import com.blog.cloud.enums.AddScene;
import com.blog.cloud.enums.VerifyUserOPCode;
import com.blog.cloud.utils.DeviceIdGenerator;
import com.blog.cloud.utils.HeaderUtils;
import com.blog.cloud.utils.WechatUtils;
import com.blog.cloud.utils.rest.StatefullRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxw
 * @date 2019/1/19
 * @description
 */
@Slf4j
@Component
public class WechatApiServiceInternal {

    @Autowired
    private WechatApiConfiguration configuration;

    @Autowired
    private WechatApiProperties properties;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    private RestTemplate restTemplate;
    private HttpHeaders postHeader;
    private HttpHeaders getHeader;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private String originValue = null;
    private String refererValue = null;

    @PostConstruct
    public void init() {
        this.restTemplate = configuration.getRestTemplate();
        this.postHeader = configuration.getPostHeader();
        this.getHeader = configuration.getGetHeader();
    }

    /**
     * Open the entry page.
     *
     * @param retryTimes retry times of qr scan
     */
    public void open(int retryTimes) {
        final String url = properties.getUrl().getEntry();
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setPragma("no-cache");
        customHeader.setCacheControl("no-cache");
        customHeader.set("Upgrade-Insecure-Requests", "1");
        customHeader.set(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        HeaderUtils.assign(customHeader, getHeader);
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
        //manually insert two cookies into cookiestore, as they're supposed to be stored in browsers by javascript.
        CookieStore store = (CookieStore) ((StatefullRestTemplate) restTemplate).getHttpContext().getAttribute(HttpClientContext.COOKIE_STORE);
        Date maxDate = new Date(Long.MAX_VALUE);
        String domain = url.replaceAll("https://", "").replaceAll("/", "");
        Map<String, String> cookies = new HashMap<>(3);
        cookies.put("MM_WX_NOTIFY_STATE", "1");
        cookies.put("MM_WX_SOUND_STATE", "1");
        if (retryTimes > 0) {
            cookies.put("refreshTimes", String.valueOf(retryTimes));
        }
        appendAdditionalCookies(store, cookies, domain, "/", maxDate);
        //It's now at entry page.
        this.originValue = url;
        this.refererValue = url.replaceAll("/$", "");
    }

    /**
     * 生成唯一标识
     *
     * @return
     */
    public String getUUID() {
        final String regEx = "window.QRLogin.code = (\\d+); window.QRLogin.uuid = \"(\\S+?)\";";
        final String url = String.format(properties.getUrl().getUuid(), System.currentTimeMillis());
        final String successCode = "200";
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setPragma("no-cache");
        customHeader.setCacheControl("no-cache");
        customHeader.setAccept(Arrays.asList(MediaType.ALL));
        customHeader.set(HttpHeaders.REFERER, properties.getUrl().getEntry());
        HeaderUtils.assign(customHeader, getHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
        String body = responseEntity.getBody();
        Matcher matcher = Pattern.compile(regEx).matcher(body);
        if (matcher.find()) {
            if (successCode.equals(matcher.group(1))) {
                return matcher.group(2);
            }
        }
        throw new RuntimeException("uuid can't be found");
    }

    /**
     * 根据微信唯一标识生成二维码
     *
     * @param uuid
     * @return
     */
    public byte[] getQR(String uuid) {
        log.info("getQR params -> " + uuid);
        final String url = properties.getUrl().getQrcode() + "/" + uuid;
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.set(HttpHeaders.ACCEPT, "image/webp,image/apng,image/*,*/*;q=0.8");
        customHeader.set(HttpHeaders.REFERER, properties.getUrl().getEntry());
        HeaderUtils.assign(customHeader, getHeader);
        ResponseEntity<byte[]> responseEntity
                = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(customHeader), new ParameterizedTypeReference<byte[]>() {
        });
        return responseEntity.getBody();
    }

    /**
     * report stats to server
     */
    public void statReport() {
        final String url = properties.getUrl().getStatreport();
        StatReportRequest request = new StatReportRequest();
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setUin("");
        baseRequest.setSid("");
        request.setBaseRequest(baseRequest);
        request.setCount(0);
        request.setList(new ArrayList<>());
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.set(HttpHeaders.REFERER, properties.getUrl().getEntry());
        customHeader.setOrigin(properties.getUrl().getEntry().replaceAll("/$", ""));
        HeaderUtils.assign(customHeader, postHeader);
        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, customHeader), String.class);
    }

    /**
     * Get hostUrl and redirectUrl
     *
     * @param uuid
     * @return hostUrl and redirectUrl
     * @throws RuntimeException if the response doesn't contain code
     */
    public LoginResponse login(String uuid) {
        log.info("login params -> " + uuid);
        final Pattern pattern = Pattern.compile("window.code=(\\d+)");
        Pattern hostUrlPattern = Pattern.compile("window.redirect_uri=\\\"(.*)\\/cgi-bin");
        Pattern redirectUrlPattern = Pattern.compile("window.redirect_uri=\\\"(.*)\\\";");
        long time = System.currentTimeMillis();
        final String url = String.format(properties.getUrl().getLogin(), uuid, -((int) time + 1), time);
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setAccept(Arrays.asList(MediaType.ALL));
        customHeader.set(HttpHeaders.REFERER, properties.getUrl().getEntry());
        HeaderUtils.assign(customHeader, getHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
        String body = responseEntity.getBody();
        Matcher matcher = pattern.matcher(body);
        LoginResponse response = new LoginResponse();
        if (matcher.find()) {
            response.setCode(matcher.group(1));
        } else {
            throw new RuntimeException("code can't be found");
        }
        Matcher hostUrlMatcher = hostUrlPattern.matcher(body);
        if (hostUrlMatcher.find()) {
            response.setHostUrl(hostUrlMatcher.group(1));
        }
        Matcher redirectUrlMatcher = redirectUrlPattern.matcher(body);
        if (redirectUrlMatcher.find()) {
            response.setRedirectUrl(redirectUrlMatcher.group(1));
        }
        return response;
    }

    /**
     * 生成Token
     *
     * @param redirectUrl
     * @return
     */
    public Token openNewloginpage(String redirectUrl) {
        log.info("openNewloginpage params -> " + redirectUrl);
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
        customHeader.set(HttpHeaders.REFERER, properties.getUrl().getEntry());
        customHeader.set("Upgrade-Insecure-Requests", "1");
        HeaderUtils.assign(customHeader, getHeader);
        customHeader.set(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9");
        redirectUrl = redirectUrl + "&fun=new&version=v2";
        Map<String, String> headers = customHeader.toSingleValueMap();

        ResponseEntity<String> responseEntity
                = restTemplate.exchange(redirectUrl, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
        String xmlString = responseEntity.getBody();

        //String xmlString = HttpUtils.httpGet(redirectUrl, headers, null);
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            Token token = xmlMapper.readValue(xmlString, Token.class);
            Map<String, String> cookies = new HashMap<>();
            List<String> lists = responseEntity.getHeaders().get("Set-Cookie");
            //wxuin=2525801323; Domain=wx.qq.com; Path=/; Expires=Wed, 23-Jan-2019 04:52:43 GMT; Secure
            CookieStore store = (CookieStore) ((StatefullRestTemplate) restTemplate).getHttpContext().getAttribute(HttpClientContext.COOKIE_STORE);
            Date maxDate = new Date(Long.MAX_VALUE);
            lists.stream().forEach(cs -> {
                String[] split = cs.split(";");
                List<String> val = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    String key = split[i].substring(0, split[i].indexOf("="));
                    String value = split[i].substring(split[i].indexOf("=") + 1, split[i].length());
                    val.add(key);
                    val.add(value);
                }

                BasicClientCookie cookie = new BasicClientCookie(val.get(0), val.get(1));
                cookie.setDomain(val.get(3));
                cookie.setPath(val.get(5));
                cookie.setExpiryDate(maxDate);
                store.addCookie(cookie);
            });
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Redirect to main page of wechat
     *
     * @param hostUrl hostUrl
     */
    public void redirect(String hostUrl) {
        log.info("redirect params -> " + hostUrl);
        final String url = hostUrl + "/";
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
        customHeader.set(HttpHeaders.REFERER, properties.getUrl().getEntry());
        customHeader.set("Upgrade-Insecure-Requests", "1");
        HeaderUtils.assign(customHeader, getHeader);
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
        //It's now at main page.
        this.originValue = hostUrl;
        this.refererValue = hostUrl + "/";
    }

    /**
     * Initialization
     *
     * @param hostUrl     hostUrl
     * @param baseRequest baseRequest
     * @return current user's information and contact information
     * @throws IOException if the http response body can't be convert to {@link InitResponse}
     */
    public InitResponse init(String hostUrl, BaseRequest baseRequest, Token token) {
        log.info("init params -> " + hostUrl + " -> " + baseRequest);
        long time = System.currentTimeMillis();
        String url = hostUrl + String.format(properties.getUrl().getInit(), -((int) time + 1), token.getPass_ticket());
        String domain = hostUrl.replaceAll("https://", "").replaceAll("/", "");
        InitRequest request = new InitRequest();
        request.setBaseRequest(baseRequest);
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.set("Host", domain);
        customHeader.set(HttpHeaders.REFERER, hostUrl + "/");
        customHeader.setOrigin(hostUrl);

        HeaderUtils.assign(customHeader, postHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, customHeader), String.class);
        try {
            return jsonMapper.readValue(WechatUtils.textDecode(responseEntity.getBody()), InitResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Notify mobile side once certain actions have been taken on web side.
     *
     * @param hostUrl     hostUrl
     * @param baseRequest baseRequest
     * @param userName    the userName of the user
     * @param code        {@link StatusNotifyCode}
     * @return the http response body
     * @throws IOException if the http response body can't be convert to {@link StatusNotifyResponse}
     */
    public StatusNotifyResponse statusNotify(String hostUrl, BaseRequest baseRequest, String userName, int code, String pass_ticket) {
        String rnd = String.valueOf(System.currentTimeMillis());
        final String url = hostUrl + String.format(properties.getUrl().getStatusNotify(), pass_ticket);
        StatusNotifyRequest request = new StatusNotifyRequest();
        request.setBaseRequest(baseRequest);
        request.setFromUserName(userName);
        request.setToUserName(userName);
        request.setCode(code);
        request.setClientMsgId(rnd);
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.set(HttpHeaders.REFERER, hostUrl + "/");
        customHeader.setOrigin(hostUrl);
        HeaderUtils.assign(customHeader, postHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, customHeader), String.class);
        try {
            return jsonMapper.readValue(WechatUtils.textDecode(responseEntity.getBody()), StatusNotifyResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all the contacts. If the Seq it returns is greater than zero, it means at least one more request is required to fetch all contacts.
     *
     * @param hostUrl hostUrl
     * @param skey    skey
     * @param seq     seq
     * @return contact information
     * @throws IOException if the http response body can't be convert to {@link GetContactResponse}
     */
    public GetContactResponse getContact(String hostUrl, String skey, long seq, String pass_ticket) {
        long rnd = System.currentTimeMillis();
        final String url = hostUrl + String.format(properties.getUrl().getGetContact(), rnd, seq, escape(skey), pass_ticket);
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
        customHeader.set(HttpHeaders.REFERER, hostUrl + "/");
        HeaderUtils.assign(customHeader, getHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
        try {
            return jsonMapper.readValue(WechatUtils.textDecode(responseEntity.getBody()), GetContactResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BatchGetContactResponse batchGetContact(String hostUrl, BaseRequest baseRequest, ChatRoomDescription[] list, Token token) {
        long rnd = System.currentTimeMillis();

        String url = hostUrl + String.format(properties.getUrl().getBatchGetContact(), rnd, token.getPass_ticket());
        BatchGetContactRequest request = new BatchGetContactRequest();
        request.setBaseRequest(baseRequest);
        request.setCount(list.length);
        request.setList(list);
        HttpHeaders customHeader = createPostCustomHeader();
        HeaderUtils.assign(customHeader, postHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, customHeader), String.class);
        try {
            return jsonMapper.readValue(WechatUtils.textDecode(responseEntity.getBody()), BatchGetContactResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SyncCheckResponse syncCheck(String hostUrl, Token token, SyncKey syncKey) {
        try {
            final Pattern pattern = Pattern.compile("window.synccheck=\\{retcode:\"(\\d+)\",selector:\"(\\d+)\"\\}");

            final String path = hostUrl + String.format(properties.getUrl().getSyncCheck(), hostUrl);
            URIBuilder builder = new URIBuilder(path);
            builder.addParameter("uin", token.getWxuin());
            builder.addParameter("sid", token.getWxsid());
            builder.addParameter("skey", token.getSkey());
            builder.addParameter("deviceid", DeviceIdGenerator.generate());
            builder.addParameter("synckey", syncKey.toString());
            builder.addParameter("r", String.valueOf(System.currentTimeMillis()));
            builder.addParameter("_", String.valueOf(System.currentTimeMillis()));

            final URI uri = builder.build().toURL().toURI();
            HttpHeaders customHeader = new HttpHeaders();
            customHeader.setAccept(Arrays.asList(MediaType.ALL));
            customHeader.set(HttpHeaders.REFERER, hostUrl + "/");
            HeaderUtils.assign(customHeader, getHeader);

            //将从微信获取到的Cookie 放入到请求头中
            CookieStore store = (CookieStore) ((StatefullRestTemplate) restTemplate).getHttpContext().getAttribute(HttpClientContext.COOKIE_STORE);
            StringBuffer buffer = new StringBuffer();
            List<Cookie> cookies = store.getCookies();
            cookies.stream().forEach(cs -> {
                String s = cs.getName() + "=" + cs.getValue() + ";";
                buffer.append(s);
            });
            customHeader.set("Cookie", buffer.toString());

            ResponseEntity<String> responseEntity
                    = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(customHeader), String.class);
            String body = responseEntity.getBody();
            Matcher matcher = pattern.matcher(body);
            if (!matcher.find()) {
                return null;
            } else {
                SyncCheckResponse result = new SyncCheckResponse();
                result.setRetcode(Integer.valueOf(matcher.group(1)));
                result.setSelector(Integer.valueOf(matcher.group(2)));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SyncResponse sync(String hostUrl, SyncKey syncKey, BaseRequest baseRequest) {
        final String url = hostUrl + String.format(properties.getUrl().getSync(), baseRequest.getSid(), escape(baseRequest.getSkey()), cacheConfiguration.getPassTicket());
        SyncRequest request = new SyncRequest();
        request.setBaseRequest(baseRequest);
        request.setRr(System.currentTimeMillis() / 1000);
        request.setSyncKey(syncKey);
        HttpHeaders customHeader = createPostCustomHeader();
        HeaderUtils.assign(customHeader, postHeader);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, customHeader), String.class);
        try {
            return jsonMapper.readValue(WechatUtils.textDecode(responseEntity.getBody()), SyncResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public VerifyUserResponse acceptFriend(String hostUrl, BaseRequest baseRequest, String passTicket, List<VerifyUser> verifyUsers) {
        try {
            final int opCode = VerifyUserOPCode.VERIFYOK.getCode();
            final Integer[] sceneList = new Integer[]{AddScene.WEB.getCode()};
            final String path = hostUrl + String.format(properties.getUrl().getVerifyUser(), cacheConfiguration.getPassTicket());

            VerifyUserRequest request = new VerifyUserRequest();
            request.setBaseRequest(baseRequest);
            request.setOpcode(opCode);
            request.setSceneList(sceneList);
            request.setSceneListCount(sceneList.length);
            request.setSkey(baseRequest.getSkey());
            request.setVerifyContent("");
            request.setVerifyUserList(verifyUsers);
            request.setVerifyUserListSize(verifyUsers.size());

            URIBuilder builder = new URIBuilder(path);
            builder.addParameter("r", String.valueOf(System.currentTimeMillis()));
            final URI uri = builder.build().toURL().toURI();

            ResponseEntity<String> responseEntity
                    = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(request, this.postHeader), String.class);
            return jsonMapper.readValue(WechatUtils.textDecode(responseEntity.getBody()), VerifyUserResponse.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void logout(String sad, String ds) {

    }

    private void appendAdditionalCookies(CookieStore store, Map<String, String> cookies, String domain, String path, Date expiryDate) {
        cookies.forEach((key, value) -> {
            BasicClientCookie cookie = new BasicClientCookie(key, value);
            cookie.setDomain(domain);
            cookie.setPath(path);
            cookie.setExpiryDate(expiryDate);
            store.addCookie(cookie);
        });
    }

    private String escape(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private HttpHeaders createPostCustomHeader() {
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.setOrigin(this.originValue);
        customHeader.set(HttpHeaders.REFERER, this.refererValue);
        return customHeader;
    }

}
