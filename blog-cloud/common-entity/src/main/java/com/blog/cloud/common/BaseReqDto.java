package com.blog.cloud.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "BaseReqDto", description = "基本请求实体类")
public class BaseReqDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "offset", value = "当前页")
    private Integer offset = 0;

    @ApiModelProperty(name = "limit", value = "每页条数")
    private Integer limit = 10;

    @ApiModelProperty(name = "dto", value = "传输类")
    private T dto;

}