package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AppInfo", description = "AppInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppInfo {

    @JsonProperty
    @ApiModelProperty(name = "AppID", value = "AppID")
    private String AppID;

    @JsonProperty
    @ApiModelProperty(name = "Type", value = "Type")
    private Integer Type;

}