package com.blog.cloud;

import com.blog.cloud.utils.SpringBootUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableHystrix
@SpringCloudApplication
@ServletComponentScan
public class BlogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServerApplication.class, args);
    }

    @Bean
    SpringBootUtil springBootUtil() {
        return new SpringBootUtil();
    }
}
