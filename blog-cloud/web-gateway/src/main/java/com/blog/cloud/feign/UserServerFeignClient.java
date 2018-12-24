package com.blog.cloud.feign;

import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.user.BlogUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-server")
public interface UserServerFeignClient {

    @PostMapping(value = "/user/register")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    RestResultBuilder registerUser(@RequestBody BlogUser user);

    @GetMapping(value = "/user/getBlogUserByUsername/{username}")
    @ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
    RestResultBuilder<BlogUser> getBlogUserByUsername(@PathVariable("username") String username);

}
