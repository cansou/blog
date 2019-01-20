package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Contact", description = "Contact")
public class MPSubscription {

    @ApiModelProperty(name = "Text", value = "Text")
    private Integer MPArticleCount;

    @ApiModelProperty(name = "MPArticleList", value = "MPArticleList")
    private List<MPArticle> MPArticleList;

    @ApiModelProperty(name = "NickName", value = "NickName")
    private String NickName;

    @ApiModelProperty(name = "Time", value = "Time")
    private Long Time;

    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;
}