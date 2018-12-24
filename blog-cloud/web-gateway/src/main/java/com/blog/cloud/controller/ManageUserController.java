package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import com.blog.cloud.domain.user.BlogUserAddDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.service.IAuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/manage/user")
@RestController
@Api(value = "ManageUserController", description = "管理用户接口")
public class ManageUserController extends BaseController {

    @Autowired
    private IAuthorizationService authorizationService;

    /**
     * 用户注册
     *
     * @param userAddDto 用户注册对象
     * @return
     */
    @PostMapping(value = "/registerUser")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public RestResultBuilder registerUser(@RequestBody BlogUserAddDto userAddDto){
        return authorizationService.registerUser(userAddDto);
    }

}
