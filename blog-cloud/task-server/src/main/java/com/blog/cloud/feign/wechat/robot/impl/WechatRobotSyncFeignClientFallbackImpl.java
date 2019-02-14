package com.blog.cloud.feign.wechat.robot.impl;

import com.blog.cloud.feign.wechat.robot.IWechatRobotSyncFeignClient;
import com.blog.cloud.http.RestResultBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@Component
public class WechatRobotSyncFeignClientFallbackImpl implements IWechatRobotSyncFeignClient {

    @Override
    public RestResultBuilder<Map<String, Object>> syncListener(String uuid) {
        return null;
    }

}
