package com.blog.cloud.feign.task;

import com.blog.cloud.domain.task.WechatRobotSyncTaskDto;
import com.blog.cloud.feign.task.impl.WechatRobotSyncFeignClientFallbackImpl;
import com.blog.cloud.http.RestResultBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "task-server", fallback = WechatRobotSyncFeignClientFallbackImpl.class)
public interface IWechatRobotSyncFeignClient {

    @RequestMapping(value = "/wechat/robot/buildSyncRemindTimer", method = RequestMethod.POST)
    public RestResultBuilder syncTaskJobCron(@RequestBody WechatRobotSyncTaskDto dto);

}
