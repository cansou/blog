package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "StatReport", description = "StatReport")
public class StatReport {

    @ApiModelProperty(name = "Text", value = "Text")
    private String Text;

    @ApiModelProperty(name = "Type", value = "Type")
    private Integer Type;
}