package com.blog.cloud.service;

import com.blog.cloud.http.RestResultBuilder;

public interface IBlogArticleService {

	/**
	 * 查询所有的文章
	 *
	 * @return RestResultBuilder
	 */
	RestResultBuilder findAllBlogArticle();

}
