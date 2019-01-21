package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Contact", description = "Contact")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPSubscription {

    @JsonProperty
    @ApiModelProperty(name = "Text", value = "Text")
    private Integer MPArticleCount;

    @JsonProperty
    @ApiModelProperty(name = "MPArticleList", value = "MPArticleList")
    private List<MPArticle> MPArticleList;

    @JsonProperty
    @ApiModelProperty(name = "NickName", value = "NickName")
    private String NickName;

    @JsonProperty
    @ApiModelProperty(name = "Time", value = "Time")
    private Long Time;

    @JsonProperty
    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;
}