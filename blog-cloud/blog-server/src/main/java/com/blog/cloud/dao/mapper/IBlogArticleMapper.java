package com.blog.cloud.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.cloud.pojo.article.BlogArticle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBlogArticleMapper extends BaseMapper<BlogArticle> {
}
