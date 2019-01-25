package com.blog.cloud.domain.request;

import com.blog.cloud.domain.shared.BaseMsg;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SendMsgRequest", description = "SendMsgRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMsgRequest {

    @JsonProperty
    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;

    @JsonProperty
    @ApiModelProperty(name = "Msg", value = "Msg")
    private BaseMsg Msg;

    @JsonProperty
    @ApiModelProperty(name = "Scene", value = "Scene")
    private Integer Scene;

}