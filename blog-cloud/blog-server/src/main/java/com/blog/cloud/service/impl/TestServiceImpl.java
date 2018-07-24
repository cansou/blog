package com.blog.cloud.service.impl;

import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.dao.TestDao;
import com.blog.cloud.pojo.article.BlogArticle;
import com.blog.cloud.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public RestResultBuilder test() {
        log.info(testDao.toString());
        BlogArticle blogArticle = testDao.selectArticle();
        return new RestResultBuilder<>(1, "success", blogArticle);
    }
}
