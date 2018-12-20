package com.blog.cloud.domain.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxw
 * @date 2018/7/28
 * @description
 */
@Data
@ApiModel(value = "BlogArticleAddDto", description = "博客文章添加数据类")
public class BlogArticleAddDto implements Serializable {

    private static final long serialVersionUID = 8110500816337461690L;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private String userId;

    @ApiModelProperty(name = "title", value = "文章标题")
    private String title;

    @ApiModelProperty(name = "text", value = "正文")
    private String text;

    @ApiModelProperty(name = "articleStatus", value = "文章状态（0 未发布 1 发布）")
    private Integer articleStatus;

}
