package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "StatusNotifyResponse", description = "StatusNotifyResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusNotifyResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "MsgID", value = "MsgID")
    private String MsgID;

}
