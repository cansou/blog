package com.blog.cloud.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author lxw
 * @date 2018/9/29
 * @description
 */
//10086		123456	read,writer	password,refresh_token
@Service
public interface UserService extends UserDetailsService {
}
