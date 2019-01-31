package com.blog.cloud.controller;

import com.blog.cloud.common.BaseReqDto;
import com.blog.cloud.domain.robot.WechatRobotUserQueryDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.robot.WechatRobotUser;
import com.blog.cloud.service.IWechatRobotUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/robot/user")
@Api(value = "WechatRobotUserController", description = "微信机器人用户控制器")
public class WechatRobotUserController extends WeChatBaseController {

    @Autowired
    private IWechatRobotUserService wechatRobotUserService;

    /**
     * 用户注册接口
     *
     * @param user
     * @return
     */
    /*@PostMapping("/listWechatRobotUser")
    @ApiOperation(value = "微信机器人用户列表", notes = "微信机器人用户列表")
    public RestResultBuilder<List<WechatRobotUser>> listWechatRobotUser(@RequestBody BaseReqDto<WechatRobotUserQueryDto> reqDto) {
        List<WechatRobotUser> wechatRobotUsers = wechatRobotUserService.listWechatRobotUser(reqDto);
        return successBuild(wechatRobotUsers);
    }*/

    /**
     * 用户注册接口
     *
     * @param user
     * @return
     */
    @PostMapping("/listWechatRobotUser")
    @ApiOperation(value = "微信机器人用户列表", notes = "微信机器人用户列表")
    public RestResultBuilder<List<WechatRobotUser>> listWechatRobotUser() {
        List<WechatRobotUser> wechatRobotUsers = wechatRobotUserService.listAllWechatRobotUser();
        return successBuild(wechatRobotUsers);
    }

}
