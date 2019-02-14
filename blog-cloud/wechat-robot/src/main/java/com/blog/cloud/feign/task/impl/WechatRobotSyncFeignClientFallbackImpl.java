package com.blog.cloud.feign.task.impl;

import com.blog.cloud.domain.task.WechatRobotSyncTaskDto;
import com.blog.cloud.feign.task.IWechatRobotSyncFeignClient;
import com.blog.cloud.http.RestResultBuilder;
import org.springframework.stereotype.Component;

@Component
public class WechatRobotSyncFeignClientFallbackImpl implements IWechatRobotSyncFeignClient {

    @Override
    public RestResultBuilder syncTaskJobCron(WechatRobotSyncTaskDto dto) {
        return null;
    }
}
