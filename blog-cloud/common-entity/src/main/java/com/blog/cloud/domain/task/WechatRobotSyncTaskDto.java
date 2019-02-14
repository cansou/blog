package com.blog.cloud.domain.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "WechatRobotSyncTaskDto", description = "微信机器人定时任务")
public class WechatRobotSyncTaskDto implements Serializable {

    private static final long serialVersionUID = 6611889030841708788L;

    @ApiModelProperty(name = "uni(jobName, jobGroup)，用户再微信的唯一标识", value = "uni")
    private String uni;

    @ApiModelProperty(name = "uuid", value = "uuid")
    private String uuid;

}
