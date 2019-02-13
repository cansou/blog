package com.blog.cloud.feign.wechat.robot;

import com.blog.cloud.feign.wechat.robot.impl.WechatRobotSyncFeignClientFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@FeignClient(name = "wechat-robot", fallback = WechatRobotSyncFeignClientFallbackImpl.class)
public interface IWechatRobotSyncFeignClient {
}
