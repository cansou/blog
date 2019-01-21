package com.blog.cloud.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "StatusNotifyRequest", description = "StatusNotifyRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusNotifyRequest {

    @JsonProperty
    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;

    @JsonProperty
    @ApiModelProperty(name = "ClientMsgId", value = "ClientMsgId")
    private String ClientMsgId;

    @JsonProperty
    @ApiModelProperty(name = "Code", value = "Code")
    private int Code;

    @JsonProperty
    @ApiModelProperty(name = "FromUserName", value = "FromUserName")
    private String FromUserName;

    @JsonProperty
    @ApiModelProperty(name = "ToUserName", value = "ToUserName")
    private String ToUserName;

}
