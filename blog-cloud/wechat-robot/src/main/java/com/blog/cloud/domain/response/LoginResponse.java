package com.blog.cloud.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "LoginResponse", description = "LoginResponse")
public class LoginResponse {

    @ApiModelProperty(name = "code", value = "code")
    private String code;

    @ApiModelProperty(name = "redirectUrl", value = "redirectUrl")
    private String redirectUrl;

    @ApiModelProperty(name = "hostUrl", value = "hostUrl")
    private String hostUrl;

}