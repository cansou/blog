package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MPArticle", description = "MPArticle")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPArticle {

    @JsonProperty
    @ApiModelProperty(name = "Title", value = "Title")
    private String Title;

    @JsonProperty
    @ApiModelProperty(name = "Digest", value = "Digest")
    private String Digest;

    @JsonProperty
    @ApiModelProperty(name = "Cover", value = "Cover")
    private String Cover;

    @JsonProperty
    @ApiModelProperty(name = "Url", value = "Url")
    private String Url;

}