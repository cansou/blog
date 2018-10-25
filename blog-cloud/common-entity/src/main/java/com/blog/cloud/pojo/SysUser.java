package com.blog.cloud.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lxw
 * @date 2018/9/29
 * @description
 */
@Setter
@Getter
public class SysUser implements Serializable {
    private String username;
    private String password;
}
