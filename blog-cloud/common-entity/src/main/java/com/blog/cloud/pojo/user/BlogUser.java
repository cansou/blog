package com.blog.cloud.pojo.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("blog_user")
@ApiModel(value = "BlogUser", description = "博客用户类")
public class BlogUser implements Serializable {

    private static final long serialVersionUID = 6038123095185159701L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(name = "id", value = "文章ID")
    private String id;

    @TableField("nickname")
    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

    @TableField("username")
    @ApiModelProperty(name = "username", value = "用户名（登陆账号）")
    private String username;

    @TableField("password")
    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    @TableField("salt")
    @ApiModelProperty(name = "salt", value = "密码盐值")
    private String salt;

    @TableField("create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Long createTime;

    @TableField("update_time")
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Long updateTime;

    @TableField("user_status")
    @ApiModelProperty(name = "userStatus", value = "用户状态（0 冻结 1 正常）")
    private Integer userStatus;

}
