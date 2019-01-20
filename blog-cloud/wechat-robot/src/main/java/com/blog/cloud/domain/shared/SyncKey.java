package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@ApiModel(value = "SyncKey", description = "SyncKey")
public class SyncKey {

    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @ApiModelProperty(name = "List", value = "List")
    private SyncKeyPair[] List;

    @Override
    public String toString() {
        if (this.List != null) {
            return String.join("|", Arrays.stream(this.List)
                    .map(x -> String.format("%s_%S", x.getKey(), x.getVal())).collect(Collectors.toList()));
        }
        return null;
    }
}
