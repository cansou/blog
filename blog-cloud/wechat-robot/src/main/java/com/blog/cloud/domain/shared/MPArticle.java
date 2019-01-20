package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MPArticle", description = "MPArticle")
public class MPArticle {

    @ApiModelProperty(name = "Title", value = "Title")
    private String Title;

    @ApiModelProperty(name = "Digest", value = "Digest")
    private String Digest;

    @ApiModelProperty(name = "Cover", value = "Cover")
    private String Cover;

    @ApiModelProperty(name = "Url", value = "Url")
    private String Url;

}