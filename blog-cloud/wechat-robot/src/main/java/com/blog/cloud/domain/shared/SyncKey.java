package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@ApiModel(value = "SyncKey", description = "SyncKey")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncKey {

    @JsonProperty
    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @JsonProperty
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
