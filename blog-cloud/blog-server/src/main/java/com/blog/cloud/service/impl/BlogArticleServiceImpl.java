package com.blog.cloud.service.impl;

import com.blog.cloud.dao.BlogArticleDao;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.article.BlogArticle;
import com.blog.cloud.service.IBlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogArticleService")
public class BlogArticleServiceImpl implements IBlogArticleService {

	@Autowired
	private BlogArticleDao blogArticleDao;

	@Override
	public RestResultBuilder findAllBlogArticle() {
		List<BlogArticle> blogArticles = blogArticleDao.selectArticle();
		return new RestResultBuilder<>(HttpStatus.OK.value(), "访问成功", blogArticles);
	}
}
