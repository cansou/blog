package com.blog.cloud.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "OpLogRequest", description = "OpLogRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpLogRequest {

    @JsonProperty
    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;

    @JsonProperty
    @ApiModelProperty(name = "CmdId", value = "CmdId")
    private Integer CmdId;

    @JsonProperty
    @ApiModelProperty(name = "RemarkName", value = "RemarkName")
    private String RemarkName;

    @JsonProperty
    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;

}