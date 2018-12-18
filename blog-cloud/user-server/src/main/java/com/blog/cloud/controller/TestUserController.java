package com.blog.cloud.controller;

import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.utils.SendMailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user/user")
@Api(value = "测试用户", description = "测试用户")
public class TestUserController {

    @Autowired
    private SendMailUtil sendMailUtil;

    @RequestMapping(value = "/findAllBlogArticle", method = RequestMethod.POST)
    @ApiOperation(value = "列出所有的文章", notes = "列出所有的文章")
    public RestResultBuilder findAllBlogArticle() {

        log.info("开始发送");

        sendMailUtil.sendSimpleMail("2030238228@qq.com", "测试发送邮件1", "这是一封测试邮件1");

//		EmailUtils.sendAccountActivateEmail();
//		EmailUtils.sendmail("hhemvoiirzdzbfih");
        log.info("发送成功");

        return new RestResultBuilder<>(HttpStatus.OK.value(), "添加成功");
    }

}
