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
@TableName("wechat_robot_friend_user")
@ApiModel(value = "WechatRobotFriendUser", description = "微信机器人用户好友表")
public class WechatRobotFriendUser implements Serializable {

	private static final long serialVersionUID = -558195512067192014L;

	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	@ApiModelProperty(name = "id", value = "数据ID")
	private String id;

	@TableField("belong_uni")
	@ApiModelProperty(name = "belongUni", value = "所属用户的Uni")
	private Long belongUni;

	@TableField("nickname")
	@ApiModelProperty(name = "nickname", value = "昵称")
	private String nickname;

	@TableField("username")
	@ApiModelProperty(name = "username", value = "用户名")
	private String username;

	@TableField("sex")
	@ApiModelProperty(name = "sex(0 女, 1 男)", value = "性别")
	private Integer sex;

	@TableField("signature")
	@ApiModelProperty(name = "signature", value = "signature")
	private String signature;

	@TableField("create_time")
	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Long createTime;

	@TableField("update_time")
	@ApiModelProperty(name = "updateTime", value = "更新时间")
	private Long updateTime;

}