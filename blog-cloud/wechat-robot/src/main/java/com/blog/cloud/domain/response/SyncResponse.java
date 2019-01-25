package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@ApiModel(value = "SyncResponse", description = "SyncResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "AddMsgCount", value = "AddMsgCount")
    private Integer AddMsgCount;

    @JsonProperty
    @ApiModelProperty(name = "AddMsgList", value = "AddMsgList")
    private List<Message> AddMsgList;

    @JsonProperty
    @ApiModelProperty(name = "ModContactCount", value = "ModContactCount")
    private Integer ModContactCount;

    @JsonProperty
    @ApiModelProperty(name = "ModContactList", value = "ModContactList")
    private Set<Contact> ModContactList;

    @JsonProperty
    @ApiModelProperty(name = "DelContactCount", value = "DelContactCount")
    private Integer DelContactCount;

    @JsonProperty
    @ApiModelProperty(name = "DelContactList", value = "DelContactList")
    private Set<Contact> DelContactList;

    @JsonProperty
    @ApiModelProperty(name = "ModChatRoomMemberCount", value = "ModChatRoomMemberCount")
    private Integer ModChatRoomMemberCount;

    @JsonProperty
    @ApiModelProperty(name = "ModChatRoomMemberList", value = "ModChatRoomMemberList")
    private Set<Contact> ModChatRoomMemberList;

    @JsonProperty
    @ApiModelProperty(name = "Profile", value = "Profile")
    private Profile Profile;

    @JsonProperty
    @ApiModelProperty(name = "ContinueFlag", value = "ContinueFlag")
    private Integer ContinueFlag;

    @JsonProperty
    @ApiModelProperty(name = "SyncKey", value = "SyncKey")
    private SyncKey SyncKey;

    @JsonProperty
    @ApiModelProperty(name = "SKey", value = "SKey")
    private String SKey;

    @JsonProperty
    @ApiModelProperty(name = "SyncCheckKey", value = "SyncCheckKey")
    private SyncCheckKey SyncCheckKey;

    
}