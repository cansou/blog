package com.blog.cloud.pojo.robot;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("wechat_robot_user")
@ApiModel(value = "WechatRobotUser", description = "微信机器人用户实体类")
public class WechatRobotUser implements Serializable {

    private static final long serialVersionUID = 778030215558456341L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(name = "id", value = "微信用户ID")
    private String id;

    @TableField("nickname")
    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;

    @TableField("username")
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    @TableField("uni")
    @ApiModelProperty(name = "uni", value = "uni")
    private Long uni;

    @TableField("sex")
    @ApiModelProperty(name = "sex(0 女, 1 男)", value = "性别")
    private Integer sex;

    @TableField("signature")
    @ApiModelProperty(name = "signature", value = "signature")
    private String signature;

    @TableField("user_status")
    @ApiModelProperty(name = "userStatus", value = "用户状态（0 冻结 1 正常）")
    private Integer userStatus;

    @TableField("create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Long createTime;

    @TableField("update_time")
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Long updateTime;

}
