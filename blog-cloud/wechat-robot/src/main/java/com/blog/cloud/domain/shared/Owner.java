package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Owner", description = "Owner")
public class Owner extends Member {

    @ApiModelProperty(name = "WebWxPluginSwitch", value = "WebWxPluginSwitch")
    private Integer WebWxPluginSwitch;

    @ApiModelProperty(name = "HeadImgFlag", value = "HeadImgFlag")
    private Integer HeadImgFlag;

}