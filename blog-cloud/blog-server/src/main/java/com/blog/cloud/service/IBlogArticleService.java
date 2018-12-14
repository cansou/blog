package com.blog.cloud.service;

import com.blog.cloud.domain.article.BlogArticleAddDto;
import com.blog.cloud.http.RestResultBuilder;

public interface IBlogArticleService {

    /**
     * 查询所有的文章
     *
     * @return RestResultBuilder
     */
    RestResultBuilder findAllBlogArticle();

    /**
     * 添加文章
     *
     * @return
     */
    RestResultBuilder addBlogArticle(BlogArticleAddDto addDto);

    RestResultBuilder sendEmail() throws Exception;

}
