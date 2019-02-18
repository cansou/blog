package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.common.BaseReqDto;
import com.blog.cloud.dao.IWechatRobotFriendUserMapper;
import com.blog.cloud.domain.robot.WechatRobotFriendUserQueryDto;
import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.pojo.robot.WechatRobotFriendUser;
import com.blog.cloud.service.IWechatRobotFriendUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("wechatRobotFriendUserService")
public class WechatRobotFriendUserServiceImpl extends ServiceImpl<IWechatRobotFriendUserMapper, WechatRobotFriendUser> implements IWechatRobotFriendUserService{

	@Override
	public List<WechatRobotFriendUser> listWechatRobotFriendUser(BaseReqDto<WechatRobotFriendUserQueryDto> reqDto) {
		return baseMapper.selectList(new EntityWrapper<>());
	}

	@Override
	public Boolean insertWechatRobotFriendUser(Set<Contact> contacts, Long uni) {
		long currentTimeMillis = System.currentTimeMillis();
		List<WechatRobotFriendUser> friendUsers = new ArrayList<>();
		contacts.stream().forEach(cs -> {
			WechatRobotFriendUser friendUser = new WechatRobotFriendUser();
			friendUser.setBelongUni(uni);
			friendUser.setNickname(cs.getNickName());
			friendUser.setUsername(cs.getUserName());
			friendUser.setSex(cs.getSex());
			friendUser.setSignature(cs.getSignature());
			friendUser.setCreateTime(currentTimeMillis);
			friendUser.setUpdateTime(currentTimeMillis);
			friendUsers.add(friendUser);
		});
		Boolean flag;
		try {
			flag = insertBatch(friendUsers, 1000);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

}
