package com.blog.cloud.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author lxw
 * @date 2019/1/9
 * @description
 */
@Data
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {

    private List<MpConfig> configs;

    @Data
    public static class MpConfig {

        @ApiModelProperty(name = "appId", value = "微信公众号的appid")
        private String appId;

        @ApiModelProperty(name = "secret", value = "微信公众号的app secret")
        private String secret;

        @ApiModelProperty(name = "token", value = "微信公众号的token")
        private String token;

        @ApiModelProperty(name = "encodingAesKey", value = "微信公众号的EncodingAESKey")
        private String encodingAesKey;

    }



}
