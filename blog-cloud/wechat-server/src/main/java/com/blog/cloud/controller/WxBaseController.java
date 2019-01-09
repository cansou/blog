package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import com.blog.cloud.config.WxMpConfiguration;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lxw
 * @date 2019/1/9
 * @description
 */
public class WxBaseController extends BaseController {

    @Autowired
    protected HttpServletRequest request;

    protected WxMpService getWxMpService(){
        Integer appId = null;
        try {
            appId = Integer.parseInt(request.getHeader("appId"));
        } catch (NumberFormatException e) {
            throw new RuntimeException("错误的AppId参数");
        }
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get(appId);
        return wxMpService;
    }

}
