package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.ChatRoomMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "CreateChatRoomResponse", description = "CreateChatRoomResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateChatRoomResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "BlackList", value = "BlackList")
    private Object BlackList;

    @JsonProperty
    @ApiModelProperty(name = "ChatRoomName", value = "ChatRoomName")
    private String ChatRoomName;

    @JsonProperty
    @ApiModelProperty(name = "MemberCount", value = "MemberCount")
    private Integer MemberCount;

    @JsonProperty
    @ApiModelProperty(name = "MemberList", value = "MemberList")
    private Set<ChatRoomMember> MemberList;

    @JsonProperty
    @ApiModelProperty(name = "PYInitial", value = "PYInitial")
    private String PYInitial;

    @JsonProperty
    @ApiModelProperty(name = "QuanPin", value = "QuanPin")
    private String QuanPin;

    @JsonProperty
    @ApiModelProperty(name = "Topic", value = "Topic")
    private String Topic;

}