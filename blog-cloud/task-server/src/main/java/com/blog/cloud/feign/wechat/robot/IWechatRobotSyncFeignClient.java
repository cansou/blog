package com.blog.cloud.feign.wechat.robot;

import com.blog.cloud.feign.wechat.robot.impl.WechatRobotSyncFeignClientFallbackImpl;
import com.blog.cloud.http.RestResultBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@FeignClient(name = "wechat-robot", fallback = WechatRobotSyncFeignClientFallbackImpl.class)
public interface IWechatRobotSyncFeignClient {

    @RequestMapping(value = "/wechat/login/syncListener/{uuid}", method = RequestMethod.GET)
    public RestResultBuilder<Map<String, Object>> syncListener(@PathVariable("uuid") String uuid);

}
