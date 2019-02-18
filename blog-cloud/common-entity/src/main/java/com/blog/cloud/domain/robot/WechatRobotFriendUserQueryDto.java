package com.blog.cloud.domain.robot;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "WechatRobotFriendUserQueryDto", description = "微信机器人用户好友查询类")
public class WechatRobotFriendUserQueryDto implements Serializable {

    private static final long serialVersionUID = -5209533741832820930L;

    @ApiModelProperty(name = "nickname", value = "用户昵称")
    private String nickname;

}
