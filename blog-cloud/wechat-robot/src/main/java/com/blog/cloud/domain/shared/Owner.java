package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Owner", description = "Owner")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner extends Member {

    @JsonProperty
    @ApiModelProperty(name = "WebWxPluginSwitch", value = "WebWxPluginSwitch")
    private Integer WebWxPluginSwitch;

    @JsonProperty
    @ApiModelProperty(name = "HeadImgFlag", value = "HeadImgFlag")
    private Integer HeadImgFlag;

}