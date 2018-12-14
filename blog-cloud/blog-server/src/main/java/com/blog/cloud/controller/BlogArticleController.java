package com.blog.cloud.controller;

import com.blog.cloud.domain.article.BlogArticleAddDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.service.IBlogArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/blog/article")
@Api(value = "博客文章控制器", description = "博客文章控制器")
public class BlogArticleController {

	@Autowired
	private IBlogArticleService blogArticleService;

	@RequestMapping(value = "/findAllBlogArticle", method = RequestMethod.POST)
	@ApiOperation(value = "列出所有的文章", notes = "列出所有的文章")
	public RestResultBuilder findAllBlogArticle() {
		return blogArticleService.findAllBlogArticle();
	}

	@RequestMapping(value = "/addBlogArticle", method = RequestMethod.POST)
	@ApiOperation(value = "添加博客", notes = "添加博客")
	public RestResultBuilder addBlogArticle(@RequestBody BlogArticleAddDto addDto) {
		return blogArticleService.addBlogArticle(addDto);
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	@ApiOperation(value = "添加博客", notes = "添加博客")
	public RestResultBuilder sendEmail() throws Exception {
		return blogArticleService.sendEmail();
	}

}
