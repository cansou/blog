package com.blog.cloud.pojo.article;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lyc
 */
@Getter
@Setter
@TableName("blog_article")
@ApiModel(value = "BlogArticle", description = "博客文章实体类")
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = -2261251044779563022L;

    @TableId("id")
    @ApiModelProperty(name = "id", value = "文章ID")
    private String id;

    @TableField("user_id")
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Integer userId;

    @TableField("title")
    @ApiModelProperty(name = "title", value = "文章标题")
    private String title;

    @TableField("text")
    @ApiModelProperty(name = "text", value = "正文")
    private String text;

    @TableField("create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Long createTime;

    @TableField("update_time")
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Long updateTime;

    @TableField("article_status")
    @ApiModelProperty(name = "articleStatus", value = "文章状态（0 未发布 1 发布）")
    private Integer articleStatus;

    @TableField("del_status")
    @ApiModelProperty(name = "delStatus", value = "删除状态（0 未删除 1 删除）")
    private Integer delStatus;

}
