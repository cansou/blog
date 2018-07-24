package com.blog.cloud.dao.mapper;

import com.blog.cloud.pojo.article.BlogArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {

    @Select("select * from blog_article limit 1")
    BlogArticle selectArticle();

}
