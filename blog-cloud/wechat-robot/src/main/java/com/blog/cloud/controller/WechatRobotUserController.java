package com.blog.cloud.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/login")
@Api(value = "WeChatLoginController", description = "微信登陆控制器")
public class WechatRobotUserController {


}
