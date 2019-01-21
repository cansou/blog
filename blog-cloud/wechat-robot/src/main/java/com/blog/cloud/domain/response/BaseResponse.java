package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BaseResponse", description = "BaseResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {

    @JsonProperty
    @ApiModelProperty(name = "Ret", value = "Ret")
    private Integer Ret;

    @JsonProperty
    @ApiModelProperty(name = "ErrMsg", value = "ErrMsg")
    private String ErrMsg;

}