package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ChatRoomDescription", description = "ChatRoomDescription")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatRoomDescription {

    @JsonProperty
    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;

    @JsonProperty
    @ApiModelProperty(name = "ChatRoomId", value = "ChatRoomId")
    private String ChatRoomId = "";

}