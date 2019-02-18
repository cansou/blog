package com.blog.cloud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.cloud.pojo.robot.WechatRobotFriendUser;
import com.blog.cloud.pojo.robot.WechatRobotUser;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface IWechatRobotFriendUserMapper extends BaseMapper<WechatRobotFriendUser> {

	public Integer batchInsertFriendUser(@Param("friendUsers") List<WechatRobotFriendUser> friendUsers);

}
