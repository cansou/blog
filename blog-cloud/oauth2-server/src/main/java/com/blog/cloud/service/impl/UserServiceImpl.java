package com.blog.cloud.service.impl;

import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lxw
 * @date 2018/9/29
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BlogUser user = new BlogUser();
        user.setUsername("10086");
        user.setPassword("123456");
        return new CustomUserDetails(user);
    }
}
