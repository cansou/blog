package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.ChatRoomMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "AddChatRoomMemberResponse", description = "AddChatRoomMemberResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddChatRoomMemberResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "MemberCount", value = "MemberCount")
    private Integer MemberCount;

    @JsonProperty
    @ApiModelProperty(name = "MemberList", value = "MemberList")
    private Set<ChatRoomMember> MemberList;

}