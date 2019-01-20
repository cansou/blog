package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SyncCheckKeyPair", description = "SyncCheckKeyPair")
public class SyncCheckKeyPair {

    @ApiModelProperty(name = "Key", value = "Key")
    private Integer Key;

    @ApiModelProperty(name = "Val", value = "Val")
    private String Val;

}
