package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "LoginResponse", description = "LoginResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    @JsonProperty
    @ApiModelProperty(name = "code", value = "code")
    private String code;

    @JsonProperty
    @ApiModelProperty(name = "redirectUrl", value = "redirectUrl")
    private String redirectUrl;

    @JsonProperty
    @ApiModelProperty(name = "hostUrl", value = "hostUrl")
    private String hostUrl;

}