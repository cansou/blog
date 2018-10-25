package com.blog.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringCloudApplication
@ServletComponentScan
@EnableEurekaClient
@EnableHystrix
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
