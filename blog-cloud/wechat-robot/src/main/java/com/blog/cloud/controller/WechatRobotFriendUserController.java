package com.blog.cloud.controller;

import com.blog.cloud.common.BaseReqDto;
import com.blog.cloud.domain.robot.WechatRobotFriendUserQueryDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.robot.WechatRobotFriendUser;
import com.blog.cloud.service.IWechatRobotFriendUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/robot/friend/user")
@Api(value = "WechatRobotFriendUserController", description = "微信机器人用户好友控制器")
public class WechatRobotFriendUserController extends WeChatBaseController {

	@Autowired
	private IWechatRobotFriendUserService wechatRobotFriendUserService;

	/**
	 * 用户注册接口
	 *
	 * @param user
	 * @return
	 */
	@PostMapping("/listWechatRobotFriendUser")
	@ApiOperation(value = "微信机器人用户列表", notes = "微信机器人用户列表")
	public RestResultBuilder<List<WechatRobotFriendUser>> listWechatRobotFriendUser(BaseReqDto<WechatRobotFriendUserQueryDto> reqDto) {
		List<WechatRobotFriendUser> wechatRobotFriendUsers = wechatRobotFriendUserService.listWechatRobotFriendUser(reqDto);
		return successBuild(wechatRobotFriendUsers);
	}

}
