package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SyncCheckKey", description = "SyncCheckKey")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncCheckKey {

    @JsonProperty
    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @JsonProperty
    @ApiModelProperty(name = "List", value = "List")
    private SyncCheckKeyPair[] List;

}
