package com.blog.cloud.service;

import com.blog.cloud.common.BaseReqDto;
import com.blog.cloud.domain.robot.WechatRobotUserQueryDto;
import com.blog.cloud.domain.shared.Owner;
import com.blog.cloud.pojo.robot.WechatRobotUser;

import java.util.List;

public interface IWechatRobotUserService {

    List<WechatRobotUser> listAllWechatRobotUser();

    List<WechatRobotUser> listWechatRobotUser(BaseReqDto<WechatRobotUserQueryDto> reqDto);

    Integer insertWechatRobotUser(Owner user);

}
