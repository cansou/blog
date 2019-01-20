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

    protected Integer appIdIndex;

    protected WxMpService getWxMpService(){
        try {
            appIdIndex = Integer.parseInt(request.getHeader("appId"));
        } catch (NumberFormatException e1) {
            try {
                appIdIndex = Integer.parseInt(request.getParameter("appId"));
            } catch (NumberFormatException e2){
                throw new RuntimeException("错误的AppId参数");
            }
        }
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get(appIdIndex);
        return wxMpService;
    }

}
