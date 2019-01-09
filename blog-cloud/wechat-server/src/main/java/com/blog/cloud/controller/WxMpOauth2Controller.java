package com.blog.cloud.controller;

import com.blog.cloud.config.WxMpConfiguration;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxw
 * @date 2019/1/9
 * @description
 */
@Slf4j
@RestController
@RequestMapping(value = "/wx/mp/oauth2")
@Api(value = "WxMpOauth2Controller", description = "用户管理")
public class WxMpOauth2Controller extends WxBaseController {

    @RequestMapping("/gotoPreAuthUrl")
    public void gotoPreAuthUrl (HttpServletResponse response) throws IOException {

        WxMpService wxMpService = getWxMpService();

        String host = request.getHeader("host");
        String url = "http://"+host+"/wx/mp/oauth2/auth/jump";
        String s = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);

        response.sendRedirect(s);
        //return s;

    }

}
