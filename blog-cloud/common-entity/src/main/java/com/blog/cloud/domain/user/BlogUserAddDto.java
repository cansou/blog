package com.blog.cloud.domain.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "BlogUserAddDto", description = "博客用户添加数据类")
public class BlogUserAddDto implements Serializable {

    private static final long serialVersionUID = -2894763563805209732L;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @ApiModelProperty(name = "username", value = "用户名（登陆账号）")
    private String username;

    @ApiModelProperty(name = "password", value = "密码")
    private String password;

}
