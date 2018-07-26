package com.blog.cloud.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.blog.cloud.dao.mapper.IBlogArticleMapper;
import com.blog.cloud.pojo.article.BlogArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogArticleDao {

	@Autowired
	private IBlogArticleMapper mapper;

	public List<BlogArticle> selectArticle() {
		return mapper.selectList(new EntityWrapper<BlogArticle>());
	}

}
