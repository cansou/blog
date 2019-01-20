package com.blog.cloud.constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxw
 * @date 2019/1/10
 * @description
 */
@ApiModel(value = "ConfigKeys", description = "wechat-server常量类")
public class ConfigKeys {

    @ApiModelProperty(name = "accessToken", value = "accessToken")
    public static final String accessToken = "mp_access_token";

}
