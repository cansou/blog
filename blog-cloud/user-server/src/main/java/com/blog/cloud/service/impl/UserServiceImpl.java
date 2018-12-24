package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.dao.IBlogUserMapper;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<IBlogUserMapper, BlogUser> implements IUserService {

    @Override
    public Integer registerUser(BlogUser user) {
        Long currentTime = System.currentTimeMillis();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        user.setUserStatus(1);
        user.setActivated(0);
        return baseMapper.insert(user);
    }

    @Override
    public BlogUser getBlogUserByUsername(String username) {
        BlogUser user = new BlogUser();
        user.setUsername(username);
        return baseMapper.selectOne(user);
    }
}
