package com.blog.cloud.pojo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "BlogArticle", description = "博客文章实体类")
public class BlogArticle {

    @ApiModelProperty(name = "id", value = "文章ID")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Integer userId;

    @ApiModelProperty(name = "title", value = "文章标题")
    private String title;

    @ApiModelProperty(name = "text", value = "正文")
    private String text;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Long createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Long updateTime;

    @ApiModelProperty(name = "articleStatus", value = "文章状态（0 未发布 1 发布）")
    private Integer articleStatus;

    @ApiModelProperty(name = "delStatus", value = "删除状态（0 未删除 1 删除）")
    private Integer delStatus;

}
