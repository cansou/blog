package com.blog.cloud.domain.request;

import com.blog.cloud.domain.shared.ChatRoomMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "CreateChatRoomRequest", description = "CreateChatRoomRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateChatRoomRequest {

    @JsonProperty
    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;

    @JsonProperty
    @ApiModelProperty(name = "MemberCount", value = "MemberCount")
    private int MemberCount;

    @JsonProperty
    @ApiModelProperty(name = "MemberList", value = "MemberList")
    private List<ChatRoomMember> MemberList;

    @JsonProperty
    @ApiModelProperty(name = "Topic", value = "Topic")
    private String Topic;
}