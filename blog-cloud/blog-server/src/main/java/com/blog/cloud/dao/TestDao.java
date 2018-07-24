package com.blog.cloud.dao;

import com.blog.cloud.dao.mapper.TestMapper;
import com.blog.cloud.pojo.article.BlogArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDao {

    @Autowired
    private TestMapper mapper;

    public BlogArticle selectArticle(){
        return mapper.selectArticle();
    }

}
