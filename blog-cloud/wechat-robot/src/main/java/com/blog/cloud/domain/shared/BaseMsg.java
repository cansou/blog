package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BaseMsg", description = "BaseMsg")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMsg {

    @JsonProperty
    @ApiModelProperty(name = "Type", value = "Type")
    private Integer Type;

    @JsonProperty
    @ApiModelProperty(name = "Content", value = "Content")
    private String Content;

    @JsonProperty
    @ApiModelProperty(name = "FromUserName", value = "FromUserName")
    private String FromUserName;

    @JsonProperty
    @ApiModelProperty(name = "ToUserName", value = "ToUserName")
    private String ToUserName;

    @JsonProperty
    @ApiModelProperty(name = "LocalID", value = "LocalID")
    private String LocalID;

    @JsonProperty
    @ApiModelProperty(name = "ClientMsgId", value = "ClientMsgId")
    private String ClientMsgId;

}