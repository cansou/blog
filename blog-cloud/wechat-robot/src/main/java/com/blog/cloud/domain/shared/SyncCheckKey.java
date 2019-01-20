package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SyncCheckKey", description = "SyncCheckKey")
public class SyncCheckKey {

    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @ApiModelProperty(name = "List", value = "List")
    private SyncCheckKeyPair[] List;

}
