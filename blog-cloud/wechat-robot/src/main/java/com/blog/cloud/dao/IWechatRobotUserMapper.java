package com.blog.cloud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.cloud.pojo.robot.WechatRobotUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IWechatRobotUserMapper extends BaseMapper<WechatRobotUser> {
}
