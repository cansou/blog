package com.blog.cloud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.cloud.pojo.robot.WechatRobotFriendUser;
import com.blog.cloud.pojo.robot.WechatRobotUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IWechatRobotFriendUserMapper extends BaseMapper<WechatRobotFriendUser> {

	public Integer batchInsertFriendUser(@Param("friendUsers") List<WechatRobotFriendUser> friendUsers);

}
