package com.blog.cloud.service.impl;

import com.blog.cloud.dao.BlogArticleDao;
import com.blog.cloud.domain.article.BlogArticleAddDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.article.BlogArticle;
import com.blog.cloud.service.IBlogArticleService;
import com.blog.cloud.utils.SendMailUtil;
import com.blog.cloud.utils.SpringBootUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("blogArticleService")
public class BlogArticleServiceImpl implements IBlogArticleService {

	@Autowired
	private BlogArticleDao blogArticleDao;

	@Autowired
	private SendMailUtil sendMailUtil;

	@Override
	public RestResultBuilder findAllBlogArticle() {
		List<BlogArticle> blogArticles = blogArticleDao.selectBlogArticle();
		return new RestResultBuilder<>(HttpStatus.OK.value(), "访问成功", blogArticles);
	}

	@Override
	public RestResultBuilder addBlogArticle(BlogArticleAddDto addDto) {
		RestResultBuilder builder = new RestResultBuilder<>(HttpStatus.OK.value(), "添加成功");
		Integer count = blogArticleDao.addBlogArticle(addDto);
		if(count == 0){
			builder.setErrCode(HttpStatus.CONTINUE.value());
			builder.setErrMsg("添加失败");
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
