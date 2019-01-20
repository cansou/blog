package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BaseResponse", description = "BaseResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class WechatHttpResponseBase {

    @ApiModelProperty(name = "BaseResponse", value = "BaseResponse")
    @JsonProperty
    private BaseResponse BaseResponse;

}