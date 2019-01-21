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

    @JsonProperty
    @ApiModelProperty(name = "BaseResponse", value = "BaseResponse")
    private BaseResponse BaseResponse;

}