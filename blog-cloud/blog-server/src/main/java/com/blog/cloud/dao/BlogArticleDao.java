package com.blog.cloud.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.blog.cloud.dao.mapper.IBlogArticleMapper;
import com.blog.cloud.domain.article.BlogArticleAddDto;
import com.blog.cloud.pojo.article.BlogArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BlogArticleDao {

	@Autowired
	private IBlogArticleMapper mapper;

	public List<BlogArticle> selectArticle() {
		Page<BlogArticle> page = new Page<BlogArticle>(0, 10);
		EntityWrapper<BlogArticle> blogWrapper = new EntityWrapper<>();
		return mapper.selectPage(new Page<>(1,10), blogWrapper.eq("title", "1"));
	}

	public Integer addBlogArticle(BlogArticleAddDto addDto){
		BlogArticle article = new BlogArticle();
		BeanUtils.copyProperties(addDto, article);
		Integer count = mapper.insert(article);
		return count;
	}

}
