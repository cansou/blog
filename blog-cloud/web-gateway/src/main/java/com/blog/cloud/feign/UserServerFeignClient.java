package com.blog.cloud.feign;

import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.user.BlogUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-server")
public interface UserServerFeignClient {

    @PostMapping(value = "/user/register")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    RestResultBuilder registerUser(@RequestBody BlogUser user);

}
