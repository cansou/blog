package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "DeleteChatRoomMemberResponse", description = "DeleteChatRoomMemberResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteChatRoomMemberResponse extends WechatHttpResponseBase {
}