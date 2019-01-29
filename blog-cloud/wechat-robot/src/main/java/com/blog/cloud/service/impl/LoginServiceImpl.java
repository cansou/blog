package com.blog.cloud.service.impl;

import com.blog.cloud.config.CacheConfiguration;
import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.request.BaseRequest;
import com.blog.cloud.domain.response.*;
import com.blog.cloud.domain.shared.ChatRoomDescription;
import com.blog.cloud.domain.shared.Token;
import com.blog.cloud.domain.shared.WechatRobotCache;
import com.blog.cloud.enums.LoginCode;
import com.blog.cloud.enums.StatusNotifyCode;
import com.blog.cloud.service.LoginService;
import com.blog.cloud.utils.QRCodeUtils;
import com.blog.cloud.utils.RedisUtil;
import com.blog.cloud.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author lxw
 * @date 2019/1/19
 * @description
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private WechatApiServiceInternal internal;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 创建二维码
     * @return
     */
    @Override
    public String createLoginQRCode() {
        //打开网页
        internal.open(0);

        //获取唯一ID
        String uuid = internal.getUUID();
        log.info("[1] uuid completed");
        log.info("[1] uuid completed {}", uuid);

        //生成登陆二维码
        byte[] qrData = internal.getQR(uuid);
        ByteArrayInputStream stream = new ByteArrayInputStream(qrData);
        String qrUrl = QRCodeUtils.decode(stream);
        log.info("[2] qrcode completed {}", qrUrl);
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        internal.statReport();
        log.info("[3] statReport completed");
        String result = qrUrl + "," + uuid;
        return result;
    }

    /**
     * 检测是否进行扫码登陆
     */
    @Override
    public LoginResponse checkScanQRCodeLogin(String uuid) {
        LoginResponse loginResponse = internal.login(uuid);
        if (LoginCode.SUCCESS.getCode().equals(loginResponse.getCode())) {
            if (loginResponse.getHostUrl() == null) {
                throw new RuntimeException("hostUrl can't be found");
            }
            if (loginResponse.getRedirectUrl() == null) {
                throw new RuntimeException("redirectUrl can't b found");
            }
            //WechatRobotCache cache = new WechatRobotCache();
            cacheConfiguration.setUuid(uuid);
            cacheConfiguration.setHostUrl(loginResponse.getHostUrl());
            if (loginResponse.getHostUrl().equals("https://wechat.com")) {
                cacheConfiguration.setSyncUrl("https://webpush.web.wechat.com");
                cacheConfiguration.setFileUrl("https://file.web.wechat.com");
            } else {
//                cache.setHostUrl("https://wx.qq.com");
                cacheConfiguration.setSyncUrl(loginResponse.getHostUrl().replaceFirst("^https://", "https://webpush."));
                cacheConfiguration.setFileUrl(loginResponse.getHostUrl().replaceFirst("^https://", "https://file."));
            }
            //redisUtil.set(uuid, cache);
        } else if (LoginCode.AWAIT_CONFIRMATION.getCode().equals(loginResponse.getCode())) {
            log.info("[*] login status = AWAIT_CONFIRMATION");
        } else if (LoginCode.AWAIT_SCANNING.getCode().equals(loginResponse.getCode())) {
            log.info("[*] login status = AWAIT_SCANNING");
        } else if (LoginCode.EXPIRED.getCode().equals(loginResponse.getCode())) {
            log.info("[*] login status = EXPIRED");
        } else {
            log.info("[*] login status = " + loginResponse.getCode());
        }
        return loginResponse;
    }

    /**
     * 微信机器人登陆
     * @param loginResponse
     * @param uuid
     */
    @Override
    public void wechatRobotLogin(LoginResponse loginResponse, String uuid) {
        //WechatRobotCache cache = redisUtil.get(uuid, WechatRobotCache.class);

        Token token = internal.openNewloginpage(loginResponse.getRedirectUrl());

        if (token.getRet() == 0) {
            cacheConfiguration.setPassTicket(token.getPass_ticket());
            cacheConfiguration.setSKey(token.getSkey());
            cacheConfiguration.setSid(token.getWxsid());
            cacheConfiguration.setUin(token.getWxuin());
            BaseRequest baseRequest = new BaseRequest();
            baseRequest.setUin(cacheConfiguration.getUin());
            baseRequest.setSid(cacheConfiguration.getSid());
            baseRequest.setSkey(cacheConfiguration.getSKey());
            cacheConfiguration.setBaseRequest(baseRequest);
        } else {
            throw new RuntimeException("token ret = " + token.getRet());
        }
        log.info("[5] redirect login completed");
        log.info("[5] redirect login completed" + token);
        cacheConfiguration.setToken(token);

        internal.redirect(cacheConfiguration.getHostUrl());
        log.info("[6] redirect completed");

        //初始化用户登陆, 返回微信生成的用户
        InitResponse initResponse = internal.init(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), token);
        //TODO  插入用户数据
        WechatUtils.checkBaseResponse(initResponse);
        cacheConfiguration.setSyncKey(initResponse.getSyncKey());
        cacheConfiguration.setOwner(initResponse.getUser());
        log.info("[7] init completed");
        log.info("[7] init initResponse" + initResponse);
        //插入数据库

        StatusNotifyResponse statusNotifyResponse = internal.statusNotify(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(),
                cacheConfiguration.getOwner().getUserName(), StatusNotifyCode.INITED.getCode(), cacheConfiguration.getPassTicket());
        WechatUtils.checkBaseResponse(statusNotifyResponse);
        log.info("[8] status notify completed");
        log.info("[8] status notify completed " + statusNotifyResponse);

        long seq = 0;
        GetContactResponse contact = internal.getContact(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest().getSkey(), seq, cacheConfiguration.getPassTicket());
        WechatUtils.checkBaseResponse(contact);
        log.info("[*] getContactResponse seq = " + contact.getSeq());
        log.info("[*] getContactResponse memberCount = " + contact.getMemberCount());
        seq = contact.getSeq();
        cacheConfiguration.getIndividuals().addAll(contact.getMemberList().stream().filter(WechatUtils::isIndividual).collect(Collectors.toSet()));
        cacheConfiguration.getMediaPlatforms().addAll(contact.getMemberList().stream().filter(WechatUtils::isMediaPlatform).collect(Collectors.toSet()));
        log.info("[9] get contact completed");

        ChatRoomDescription[] chatRoomDescriptions = initResponse.getContactList().stream()
                .filter(x -> x != null && WechatUtils.isChatRoom(x))
                .map(x -> {
                    ChatRoomDescription description = new ChatRoomDescription();
                    description.setUserName(x.getUserName());
                    return description;
                })
                .toArray(ChatRoomDescription[]::new);
        if (chatRoomDescriptions.length > 0) {
            BatchGetContactResponse batchGetContactResponse = internal.batchGetContact(
                    cacheConfiguration.getHostUrl(),
                    cacheConfiguration.getBaseRequest(),
                    chatRoomDescriptions, token);
            WechatUtils.checkBaseResponse(batchGetContactResponse);
            log.info("[*] batchGetContactResponse count = " + batchGetContactResponse.getCount());
            cacheConfiguration.getChatRooms().addAll(batchGetContactResponse.getContactList());
        }

        log.info("[10] batch get contact completed");
        cacheConfiguration.setAlive(true);
        log.info("[*] login process completed");
        log.info("[*] start listening");
        //redisUtil.set(uuid, cache);
    }

    @Override
    public void login() {
        internal.open(0);

        //获取唯一ID
        String uuid = internal.getUUID();
        log.info("[1] uuid completed");
        log.info("[1] uuid completed " + uuid);

        //生成登陆二维码
        byte[] qrData = internal.getQR(uuid);
        ByteArrayInputStream stream = new ByteArrayInputStream(qrData);
        String qrUrl = QRCodeUtils.decode(stream);
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String qr = QRCodeUtils.generateQR(qrUrl, 20, 20);
        log.info("\r\n" + qr);
        log.info("[2] qrcode completed");

        //statReport
        internal.statReport();
        log.info("[3] statReport completed");

        LoginResponse loginResponse;
        while (true) {
            loginResponse = internal.login(uuid);
            if (LoginCode.SUCCESS.getCode().equals(loginResponse.getCode())) {
                if (loginResponse.getHostUrl() == null) {
                    throw new RuntimeException("hostUrl can't be found");
                }
                if (loginResponse.getRedirectUrl() == null) {
                    throw new RuntimeException("redirectUrl can't be found");
                }
                /*if ("https://wx2.qq.com".equals(loginResponse.getHostUrl())) {
                    loginResponse.setHostUrl("https://wx.qq.com");
                }*/
                cacheConfiguration.setHostUrl(loginResponse.getHostUrl());
                if (loginResponse.getHostUrl().equals("https://wechat.com")) {
                    cacheConfiguration.setSyncUrl("https://webpush.web.wechat.com");
                    cacheConfiguration.setFileUrl("https://file.web.wechat.com");
                } else {
                    //cacheConfiguration.setHostUrl("https://wx.qq.com");
                    cacheConfiguration.setSyncUrl(loginResponse.getHostUrl().replaceFirst("^https://", "https://webpush."));
                    cacheConfiguration.setFileUrl(loginResponse.getHostUrl().replaceFirst("^https://", "https://file."));
                }
                break;
            } else if (LoginCode.AWAIT_CONFIRMATION.getCode().equals(loginResponse.getCode())) {
                log.info("[*] login status = AWAIT_CONFIRMATION");
            } else if (LoginCode.AWAIT_SCANNING.getCode().equals(loginResponse.getCode())) {
                log.info("[*] login status = AWAIT_SCANNING");
            } else if (LoginCode.EXPIRED.getCode().equals(loginResponse.getCode())) {
                log.info("[*] login status = EXPIRED");
                throw new RuntimeException();
            } else {
                log.info("[*] login status = " + loginResponse.getCode());
            }
        }
        log.info("[4] login completed");

        Token token = internal.openNewloginpage(loginResponse.getRedirectUrl());
        if (token.getRet() == 0) {
            cacheConfiguration.setPassTicket(token.getPass_ticket());
            cacheConfiguration.setSKey(token.getSkey());
            cacheConfiguration.setSid(token.getWxsid());
            cacheConfiguration.setUin(token.getWxuin());
            BaseRequest baseRequest = new BaseRequest();
            baseRequest.setUin(cacheConfiguration.getUin());
            baseRequest.setSid(cacheConfiguration.getSid());
            baseRequest.setSkey(cacheConfiguration.getSKey());
            cacheConfiguration.setBaseRequest(baseRequest);
        } else {
            throw new RuntimeException("token ret = " + token.getRet());
        }
        log.info("[5] redirect login completed");
        log.info("[5] redirect login completed" + token);
        cacheConfiguration.setToken(token);

        internal.redirect(cacheConfiguration.getHostUrl());
        log.info("[6] redirect completed");

        InitResponse initResponse = internal.init(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), token);
        WechatUtils.checkBaseResponse(initResponse);
        cacheConfiguration.setSyncKey(initResponse.getSyncKey());
        cacheConfiguration.setOwner(initResponse.getUser());
        log.info("[7] init completed");
        log.info("[7] init initResponse" + initResponse);

        StatusNotifyResponse statusNotifyResponse = internal.statusNotify(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(),
                cacheConfiguration.getOwner().getUserName(), StatusNotifyCode.INITED.getCode(), cacheConfiguration.getPassTicket());
        WechatUtils.checkBaseResponse(statusNotifyResponse);
        log.info("[8] status notify completed");
        log.info("[8] status notify completed " + statusNotifyResponse);

        long seq = 0;
        GetContactResponse contact = internal.getContact(cacheConfiguration.getHostUrl(),
                cacheConfiguration.getBaseRequest().getSkey(), seq, cacheConfiguration.getPassTicket());
        WechatUtils.checkBaseResponse(contact);
        log.info("[*] getContactResponse seq = " + contact.getSeq());
        log.info("[*] getContactResponse memberCount = " + contact.getMemberCount());
        seq = contact.getSeq();
        cacheConfiguration.getIndividuals().addAll(contact.getMemberList().stream().filter(WechatUtils::isIndividual).collect(Collectors.toSet()));
        cacheConfiguration.getMediaPlatforms().addAll(contact.getMemberList().stream().filter(WechatUtils::isMediaPlatform).collect(Collectors.toSet()));
        log.info("[9] get contact completed");

        ChatRoomDescription[] chatRoomDescriptions = initResponse.getContactList().stream()
                .filter(x -> x != null && WechatUtils.isChatRoom(x))
                .map(x -> {
                    ChatRoomDescription description = new ChatRoomDescription();
                    description.setUserName(x.getUserName());
                    return description;
                })
                .toArray(ChatRoomDescription[]::new);
        if (chatRoomDescriptions.length > 0) {
            BatchGetContactResponse batchGetContactResponse = internal.batchGetContact(
                    cacheConfiguration.getHostUrl(),
                    cacheConfiguration.getBaseRequest(),
                    chatRoomDescriptions, token);
            WechatUtils.checkBaseResponse(batchGetContactResponse);
            log.info("[*] batchGetContactResponse count = " + batchGetContactResponse.getCount());
            cacheConfiguration.getChatRooms().addAll(batchGetContactResponse.getContactList());
        }

        log.info("[10] batch get contact completed");
        cacheConfiguration.setAlive(true);
        log.info("[*] login process completed");
        log.info("[*] start listening");
    }

}
