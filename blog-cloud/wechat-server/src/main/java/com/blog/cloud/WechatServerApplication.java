package com.blog.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableFeignClients
@EnableScheduling
@EnableEurekaClient
@ServletComponentScan
@EnableHystrix
@SpringCloudApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class WechatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatServerApplication.class, args);
    }

}

