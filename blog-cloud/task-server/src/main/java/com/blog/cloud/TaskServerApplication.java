package com.blog.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableEurekaClient
@ServletComponentScan
@EnableHystrix
@SpringCloudApplication
public class TaskServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskServerApplication.class, args);
    }

}

