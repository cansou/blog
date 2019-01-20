package com.blog.cloud.config;

import com.blog.cloud.utils.HeaderUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxw
 * @date 2019/1/19
 * @description
 */
@Data
@Component
@EnableConfigurationProperties(WechatApiProperties.class)
public class WechatApiConfiguration {

    private WechatApiProperties properties;

    private RestTemplate restTemplate;
    private HttpHeaders postHeader;
    private HttpHeaders getHeader;
    private ObjectMapper jsonMapper = new ObjectMapper();
    //private static final String originValue = null;
    //private String refererValue = null;
    private static final String BROWSER_DEFAULT_ACCEPT_LANGUAGE = "en,zh-CN;q=0.8,zh;q=0.6,ja;q=0.4,zh-TW;q=0.2";
    private static final String BROWSER_DEFAULT_ACCEPT_ENCODING = "gzip, deflate, br";

    @Autowired
    public WechatApiConfiguration(WechatApiProperties properties, RestTemplate restTemplate) {
        this.properties = properties;

        this.restTemplate = restTemplate;

        this.postHeader = new HttpHeaders();
        postHeader.set(HttpHeaders.USER_AGENT, properties.getUa());
        postHeader.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        postHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
        postHeader.set(HttpHeaders.ACCEPT_LANGUAGE, BROWSER_DEFAULT_ACCEPT_LANGUAGE);
        postHeader.set(HttpHeaders.ACCEPT_ENCODING, BROWSER_DEFAULT_ACCEPT_ENCODING);

        this.getHeader = new HttpHeaders();
        getHeader.set(HttpHeaders.USER_AGENT, properties.getUa());
        getHeader.set(HttpHeaders.ACCEPT_LANGUAGE, BROWSER_DEFAULT_ACCEPT_LANGUAGE);
        getHeader.set(HttpHeaders.ACCEPT_ENCODING, BROWSER_DEFAULT_ACCEPT_ENCODING);
    }



}
