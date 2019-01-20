package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.domain.shared.MPSubscription;
import com.blog.cloud.domain.shared.Owner;
import com.blog.cloud.domain.shared.SyncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@ApiModel(value = "InitResponse", description = "InitResponse")
public class InitResponse extends WechatHttpResponseBase {

    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @ApiModelProperty(name = "ContactList", value = "ContactList")
    private Set<Contact> ContactList;

    @ApiModelProperty(name = "SyncKey", value = "SyncKey")
    private SyncKey SyncKey;

    @ApiModelProperty(name = "User", value = "User")
    private Owner User;

    @ApiModelProperty(name = "ChatSet", value = "ChatSet")
    private String ChatSet;

    @ApiModelProperty(name = "SKey", value = "SKey")
    private String SKey;

    @ApiModelProperty(name = "ClientVersion", value = "ClientVersion")
    private String ClientVersion;

    @ApiModelProperty(name = "SystemTime", value = "SystemTime")
    private Long SystemTime;

    @ApiModelProperty(name = "GrayScale", value = "GrayScale")
    private Integer GrayScale;

    @ApiModelProperty(name = "InviteStartCount", value = "InviteStartCount")
    private Integer InviteStartCount;

    @ApiModelProperty(name = "MPSubscribeMsgCount", value = "MPSubscribeMsgCount")
    private Integer MPSubscribeMsgCount;

    @ApiModelProperty(name = "MPSubscribeMsgList", value = "MPSubscribeMsgList")
    private List<MPSubscription> MPSubscribeMsgList;

    @ApiModelProperty(name = "ClickReportInterval", value = "ClickReportInterval")
    private Long ClickReportInterval;

}