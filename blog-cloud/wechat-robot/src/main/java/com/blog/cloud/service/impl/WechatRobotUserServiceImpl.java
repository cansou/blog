package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.dao.IWechatRobotUserMapper;
import com.blog.cloud.domain.shared.Owner;
import com.blog.cloud.pojo.robot.WechatRobotUser;
import com.blog.cloud.service.IWechatRobotUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("wechatRobotUserService")
public class WechatRobotUserServiceImpl extends ServiceImpl<IWechatRobotUserMapper, WechatRobotUser> implements IWechatRobotUserService {

    @Override
    public Integer insertWechatRobotUser(Owner user) {
        long currentTimeMillis = System.currentTimeMillis();
        WechatRobotUser entity = new WechatRobotUser();
        entity.setNickname(user.getNickName());
        entity.setUsername(user.getUserName());
        entity.setSex(user.getSex());
        entity.setUni(user.getUin());
        entity.setSignature(user.getSignature());
        entity.setUpdateTime(currentTimeMillis);
        entity.setCreateTime(currentTimeMillis);
        entity.setUserStatus(1);
        return baseMapper.insert(entity);
    }
}
