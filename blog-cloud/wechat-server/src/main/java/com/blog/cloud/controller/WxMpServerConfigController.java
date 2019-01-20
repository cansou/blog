package com.blog.cloud.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author lxw
 * @date 2019/1/10
 * @description
 */
@Slf4j
@RestController
@RequestMapping(value = "/wx/mp/server/config")
@Api(value = "WxMpServerConfigController", description = "微信服务器配置Controller")
public class WxMpServerConfigController extends WxBaseController {

    @GetMapping("/checkSignature")
    public String cinfigWxMpServer(){

        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");

        WxMpService wxMpService = getWxMpService();
        String token = wxMpService.getWxMpConfigStorage().getToken();
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        String tmpStr  = DigestUtils.sha1Hex(content.toString()).toUpperCase();
        if (signature.toUpperCase().equals(tmpStr)) {
            return echostr;
        }

        return null;
    }


}
