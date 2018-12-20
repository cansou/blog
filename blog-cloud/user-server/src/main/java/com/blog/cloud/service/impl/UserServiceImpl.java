package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.dao.IBlogUserMapper;
import com.blog.cloud.pojo.user.BlogUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<IBlogUserMapper, BlogUser> {
}
