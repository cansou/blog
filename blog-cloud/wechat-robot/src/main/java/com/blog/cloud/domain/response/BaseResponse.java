package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BaseResponse", description = "BaseResponse")
public class BaseResponse {

    @ApiModelProperty(name = "Ret", value = "Ret")
    @JsonProperty
    private Integer Ret;

    @ApiModelProperty(name = "ErrMsg", value = "ErrMsg")
    @JsonProperty
    private String ErrMsg;

}