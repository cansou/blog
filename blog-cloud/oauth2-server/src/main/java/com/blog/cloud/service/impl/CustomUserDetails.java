package com.blog.cloud.service.impl;

import com.blog.cloud.pojo.SysUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

/**
 * @author lxw
 * @date 2018/9/29
 * @description
 */
@Getter
@Setter
public class CustomUserDetails extends User {
    private SysUser sysUser;

    public CustomUserDetails(SysUser sysUser) {
        super(sysUser.getUsername(), sysUser.getPassword(), true, true, true, true, Collections.EMPTY_SET);
        this.sysUser = sysUser;
    }
}
