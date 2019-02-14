package com.blog.cloud;

import com.blog.cloud.utils.rest.StatefullRestTemplate;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients
@EnableScheduling
@EnableEurekaClient
@ServletComponentScan
@EnableHystrix
@SpringCloudApplication
public class WechatRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatRobotApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate () {
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext httpContext = new BasicHttpContext();
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        httpContext.setAttribute(HttpClientContext.REQUEST_CONFIG, RequestConfig.custom().setRedirectsEnabled(false).build());
        return new StatefullRestTemplate(httpContext);
    }

}

