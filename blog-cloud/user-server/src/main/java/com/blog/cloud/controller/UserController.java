package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import com.blog.cloud.domain.user.BlogUserAddDto;
import com.blog.cloud.http.RestResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@Api(value = "测试用户", description = "测试用户")
public class UserController extends BaseController {

    /**
     * 用户注册接口
     * @param userAddDto
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "列出所有的文章", notes = "列出所有的文章")
    public RestResultBuilder register (@RequestBody BlogUserAddDto userAddDto) {
        return successBuild(null);
    }


}
