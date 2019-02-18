package com.blog.cloud.service;

import com.blog.cloud.common.BaseReqDto;
import com.blog.cloud.domain.robot.WechatRobotFriendUserQueryDto;
import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.pojo.robot.WechatRobotFriendUser;

import java.util.List;
import java.util.Set;

public interface IWechatRobotFriendUserService {

	List<WechatRobotFriendUser> listWechatRobotFriendUser(BaseReqDto<WechatRobotFriendUserQueryDto> reqDto);

	Boolean insertWechatRobotFriendUser(Set<Contact> contacts, Long uni);

}
