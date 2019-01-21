package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.domain.shared.MPSubscription;
import com.blog.cloud.domain.shared.Owner;
import com.blog.cloud.domain.shared.SyncKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@ApiModel(value = "InitResponse", description = "InitResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @JsonProperty
    @ApiModelProperty(name = "ContactList", value = "ContactList")
    private Set<Contact> ContactList;

    @JsonProperty
    @ApiModelProperty(name = "SyncKey", value = "SyncKey")
    private SyncKey SyncKey;

    @JsonProperty
    @ApiModelProperty(name = "User", value = "User")
    private Owner User;

    @JsonProperty
    @ApiModelProperty(name = "ChatSet", value = "ChatSet")
    private String ChatSet;

    @JsonProperty
    @ApiModelProperty(name = "SKey", value = "SKey")
    private String SKey;

    @JsonProperty
    @ApiModelProperty(name = "ClientVersion", value = "ClientVersion")
    private String ClientVersion;

    @JsonProperty
    @ApiModelProperty(name = "SystemTime", value = "SystemTime")
    private Long SystemTime;

    @JsonProperty
    @ApiModelProperty(name = "GrayScale", value = "GrayScale")
    private Integer GrayScale;

    @JsonProperty
    @ApiModelProperty(name = "InviteStartCount", value = "InviteStartCount")
    private Integer InviteStartCount;

    @JsonProperty
    @ApiModelProperty(name = "MPSubscribeMsgCount", value = "MPSubscribeMsgCount")
    private Integer MPSubscribeMsgCount;

    @JsonProperty
    @ApiModelProperty(name = "MPSubscribeMsgList", value = "MPSubscribeMsgList")
    private List<MPSubscription> MPSubscribeMsgList;

    @JsonProperty
    @ApiModelProperty(name = "ClickReportInterval", value = "ClickReportInterval")
    private Long ClickReportInterval;

}