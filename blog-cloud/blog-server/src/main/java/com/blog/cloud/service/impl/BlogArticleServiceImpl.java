package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.dao.IBlogArticleMapper;
import com.blog.cloud.domain.article.BlogArticleAddDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.article.BlogArticle;
import com.blog.cloud.service.IBlogArticleService;
import com.blog.cloud.utils.DateUtil;
import com.blog.cloud.utils.SendMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("blogArticleService")
public class BlogArticleServiceImpl extends ServiceImpl<IBlogArticleMapper, BlogArticle> implements IBlogArticleService {


	@Autowired
	private SendMailUtil sendMailUtil;

	@Override
	public RestResultBuilder findAllBlogArticle() {
		Page<BlogArticle> page = new Page<>(0, 10);
		EntityWrapper<BlogArticle> blogWrapper = new EntityWrapper<>();
		List<BlogArticle> blogArticles = baseMapper.selectPage(page, blogWrapper.eq("title", "1"));
		return new RestResultBuilder<>(HttpStatus.OK.value(), "访问成功", blogArticles);
	}

	@Override
	public RestResultBuilder addBlogArticle(BlogArticleAddDto addDto) {
		RestResultBuilder builder = new RestResultBuilder<>(HttpStatus.OK.value(), "添加成功");
		Long currentTimeMillis = DateUtil.getCurrentTimeMillis();

		BlogArticle article = new BlogArticle();
		BeanUtils.copyProperties(addDto, article);
		article.setCreateTime(currentTimeMillis);
		article.setUpdateTime(currentTimeMillis);
		article.setArticleStatus(1);
		article.setDelStatus(0);
		Integer count = baseMapper.insert(article);

		if(count == 0){
			builder.setResultCode(HttpStatus.CONTINUE.value());
			builder.setResultMsg("添加失败");
		}
		return builder;
	}

	@Override
	public RestResultBuilder sendEmail() throws Exception {
		log.info("开始发送");

		sendMailUtil.sendSimpleMail("2030238228@qq.com", "测试发送邮件", "这是一封测试邮件");

//		EmailUtils.sendAccountActivateEmail();
//		EmailUtils.sendmail("hhemvoiirzdzbfih");
		log.info("发送成功");
		return new RestResultBuilder<>(HttpStatus.OK.value(), "添加成功");
	}
}
