package com.blog.cloud.controller;

import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.service.IBlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blog/article")
public class BlogArticleController {

	@Autowired
	private IBlogArticleService blogArticleService;

	@RequestMapping(value = "/findAllBlogArticle", method = RequestMethod.GET)
	public RestResultBuilder findAllBlogArticle() {
		return blogArticleService.findAllBlogArticle();
	}

}
