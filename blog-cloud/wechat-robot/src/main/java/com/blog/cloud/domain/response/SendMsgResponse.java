package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SendMsgResponse", description = "SendMsgResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMsgResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "MsgID", value = "MsgID")
    private String MsgID;

    @JsonProperty
    @ApiModelProperty(name = "LocalID", value = "LocalID")
    private String LocalID;

}