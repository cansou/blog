package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "StatusNotifyResponse", description = "StatusNotifyResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncCheckResponse {

    @JsonProperty
    @ApiModelProperty(name = "retcode", value = "retcode")
    private Integer retcode;

    @JsonProperty
    @ApiModelProperty(name = "selector", value = "selector")
    private Integer selector;
}