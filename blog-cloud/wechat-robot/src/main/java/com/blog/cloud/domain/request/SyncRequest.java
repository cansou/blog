package com.blog.cloud.domain.request;

import com.blog.cloud.domain.shared.SyncKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SyncRequest", description = "SyncRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncRequest {

    @JsonProperty
    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;

    @JsonProperty
    @ApiModelProperty(name = "rr", value = "rr")
    private Long rr;

    @JsonProperty
    @ApiModelProperty(name = "SyncKey", value = "SyncKey")
    private SyncKey SyncKey;

}