package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SyncKeyPair", description = "SyncKeyPair")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncKeyPair {

    @JsonProperty
    @ApiModelProperty(name = "Key", value = "Key")
    private Integer Key;

    @JsonProperty
    @ApiModelProperty(name = "Val", value = "Val")
    private String Val;

}
