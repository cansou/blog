package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "StatReport", description = "StatReport")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatReport {

    @JsonProperty
    @ApiModelProperty(name = "Text", value = "Text")
    private String Text;

    @JsonProperty
    @ApiModelProperty(name = "Type", value = "Type")
    private Integer Type;
}