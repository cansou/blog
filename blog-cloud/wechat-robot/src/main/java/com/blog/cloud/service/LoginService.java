package com.blog.cloud.service;

import com.blog.cloud.domain.response.LoginResponse;

/**
 * @author lxw
 * @date 2019/1/19
 * @description
 */
public interface LoginService {

    /**
     * 创建二维码
     * @return
     */
    String createLoginQRCode();

    /**
     * 检测是否进行扫码登陆
     */
    LoginResponse checkScanQRCodeLogin(String uuid);

    /**
     * 微信机器人登陆
     * @param loginResponse
     * @param uuid
     */
    void wechatRobotLogin(LoginResponse loginResponse, String uuid);

}
