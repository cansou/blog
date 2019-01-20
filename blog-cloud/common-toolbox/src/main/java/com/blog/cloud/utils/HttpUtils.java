package com.blog.cloud.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author lxw
 * @date 2019/1/19
 * @description
 */
@Slf4j
public class HttpUtils {

    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_OPTION = "OPTION";

    private static final String PROTOCOL_HTTP = "HTTP";
    private static final String PROTOCOL_HTTPS = "HTTPS";

    private static final String emptyStr = "";

    /**
     * Http方式
     * 顶级post请求构造，以payload 的形式提交
     *
     * @param url
     * @param headers
     * @param payload
     * @return
     */
    public static String httpPostForPayload(String url, Map<String, String> headers, JSONObject payload) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            if (headers != null) {
                headers.entrySet().stream().forEach(hs -> {
                    httpPost.setHeader(hs.getKey(), hs.getValue());
                });
            }
            StringEntity reqEntity;
            if (payload != null) {
                reqEntity = new StringEntity(payload.toJSONString(), Consts.UTF_8);
            } else {
                reqEntity = new StringEntity(emptyStr, Consts.UTF_8);
            }
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    throw new RuntimeException(statusCode + emptyStr);
                }
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, Consts.UTF_8);
                return result;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    log.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    public static String httpPostForPayload(String url, JSONObject payload) {
        return httpPostForPayload(url, null, payload);
    }

    public static String httpPostForPayload(String url) {
        return httpPostForPayload(url, null, null);
    }


    /**
     * Https方式
     * 顶级post请求构造，以payload 的形式提交
     *
     * @param url
     * @param headers
     * @param payload
     * @return
     */
    public static String httpsPostForPayload(String url, Map<String, String> headers, JSONObject payload) {
        CloseableHttpClient httpclient = buildCloseableHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            if (headers != null) {
                headers.entrySet().stream().forEach(hs -> {
                    httpPost.setHeader(hs.getKey(), hs.getValue());
                });
            }
            StringEntity reqEntity;
            if (payload != null) {
                reqEntity = new StringEntity(payload.toJSONString(), Consts.UTF_8);
            } else {
                reqEntity = new StringEntity(emptyStr, Consts.UTF_8);
            }
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    throw new RuntimeException(statusCode + emptyStr);
                }
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, Consts.UTF_8);
                return result;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    log.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    public static String httpsPostForPayload(String url, JSONObject payload) {
        return httpsPostForPayload(url, null, payload);
    }

    public static String httpsPostForPayload(String url) {
        return httpsPostForPayload(url, null, null);
    }

    /**
     * Http方式
     * 顶级get请求构造，以params 的形式提交
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String httpGet(String url, Map<String, String> headers, Map<String, String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            if (headers != null) {
                headers.entrySet().stream().forEach(hs -> {
                    httpGet.setHeader(hs.getKey(), hs.getValue());
                });
            }
            if (params != null) {
                StringBuffer buffer = new StringBuffer("?");
                params.entrySet().stream().forEach(ps -> {
                    buffer.append(ps.getKey()).append("=").append(ps.getValue()).append("&");
                });
                String param = buffer.deleteCharAt(buffer.length() - 1).toString();
                url = url + param;
                httpGet.setURI(URI.create(url));
            }
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    throw new RuntimeException(statusCode + emptyStr);
                }
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, Consts.UTF_8);
                return result;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    log.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    public static String httpGet(String url, Map<String, String> params) {
        return httpGet(url, null, params);
    }

    public static String httpGet(String url) {
        return httpGet(url, null, null);
    }

    /**
     * Https方式
     * 顶级get请求构造，以params 的形式提交
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String httpsGet(String url, Map<String, String> headers, Map<String, String> params) {
        CloseableHttpClient httpclient = buildCloseableHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            if (headers != null) {
                headers.entrySet().stream().forEach(hs -> {
                    httpGet.setHeader(hs.getKey(), hs.getValue());
                });
            }
            if (params != null) {
                StringBuffer buffer = new StringBuffer("?");
                params.entrySet().stream().forEach(ps -> {
                    buffer.append(ps.getKey()).append("=").append(ps.getValue()).append("&");
                });
                String param = buffer.deleteCharAt(buffer.length() - 1).toString();
                url = url + param;
                httpGet.setURI(URI.create(url));
            }
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    throw new RuntimeException(statusCode + emptyStr);
                }
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, Consts.UTF_8);
                return result;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    log.error("http关闭CloseableHttpResponse失败");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.error("http关闭CloseableHttpClient失败");
                e.printStackTrace();
            }
        }
    }

    public static String httpsGet(String url, Map<String, String> params) {
        return httpsGet(url, null, params);
    }

    public static String httpsGet(String url) {
        return httpsGet(url, null, null);
    }


    private static CloseableHttpClient buildCloseableHttpClient() {
        SSLContext sslContext = null;
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            sslContext = SSLContextBuilder.create().build();
            sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

}
