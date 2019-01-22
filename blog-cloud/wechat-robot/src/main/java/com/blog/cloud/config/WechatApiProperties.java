package com.blog.cloud.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lxw
 * @date 2019/1/19
 * @description
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@ApiModel(value = "WechatApiProperties", description = "WechatApiProperties")
public class WechatApiProperties {

    private String ua;

    private ApiConfigUrl url;

    @Data
    public static class ApiConfigUrl {

        @ApiModelProperty(name = "entry", value = "entry")
        private String entry;

        @ApiModelProperty(name = "loginBase", value = "loginBase")
        private String loginBase;

        @ApiModelProperty(name = "pathBase", value = "pathBase")
        private String pathBase;

        @ApiModelProperty(name = "uuid", value = "uuid")
        private String uuid;

        @ApiModelProperty(name = "qrcode", value = "qrcode")
        private String qrcode;

        @ApiModelProperty(name = "login", value = "login")
        private String login;

        @ApiModelProperty(name = "init", value = "init")
        private String init;

        @ApiModelProperty(name = "statreport", value = "statreport")
        private String statreport;

        @ApiModelProperty(name = "statusNotify", value = "statusNotify")
        private String statusNotify;

        @ApiModelProperty(name = "syncCheck", value = "syncCheck")
        private String syncCheck;

        @ApiModelProperty(name = "sync", value = "sync")
        private String sync;

        @ApiModelProperty(name = "getContact", value = "getContact")
        private String getContact;

        @ApiModelProperty(name = "sendMsg", value = "sendMsg")
        private String sendMsg;

        @ApiModelProperty(name = "uploadMedia", value = "uploadMedia")
        private String uploadMedia;

        @ApiModelProperty(name = "getMsgImg", value = "getMsgImg")
        private String getMsgImg;

        @ApiModelProperty(name = "getVoice", value = "getVoice")
        private String getVoice;

        @ApiModelProperty(name = "getVideo", value = "getVideo")
        private String getVideo;

        @ApiModelProperty(name = "pushLogin", value = "pushLogin")
        private String pushLogin;

        @ApiModelProperty(name = "logout", value = "logout")
        private String logout;

        @ApiModelProperty(name = "batchGetContact", value = "batchGetContact")
        private String batchGetContact;

        @ApiModelProperty(name = "opLog", value = "opLog")
        private String opLog;

        @ApiModelProperty(name = "verifyUser", value = "verifyUser")
        private String verifyUser;

        @ApiModelProperty(name = "getMedia", value = "getMedia")
        private String getMedia;

        @ApiModelProperty(name = "createChatroom", value = "createChatroom")
        private String createChatroom;

        @ApiModelProperty(name = "deleteChatroomMember", value = "deleteChatroomMember")
        private String deleteChatroomMember;

        @ApiModelProperty(name = "addChatroomMember", value = "addChatroomMember")
        private String addChatroomMember;

    }

}
