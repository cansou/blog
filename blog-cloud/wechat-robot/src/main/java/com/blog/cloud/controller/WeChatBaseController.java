package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lxw
 * @date 2019/1/9
 * @description
 */
public class WeChatBaseController extends BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

}
