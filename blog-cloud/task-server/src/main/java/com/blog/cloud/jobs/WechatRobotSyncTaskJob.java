package com.blog.cloud.jobs;

import com.blog.cloud.feign.wechat.robot.IWechatRobotSyncFeignClient;
import com.blog.cloud.http.RestResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@Slf4j
@Component
public class WechatRobotSyncTaskJob implements Job {

    @Autowired
    private IWechatRobotSyncFeignClient wechatRobotSyncFeignClient;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("=========================定时任务每3秒执行一次===============================");
        System.out.println("jobName=====:"+context.getJobDetail().getKey().getName());
        System.out.println("jobGroup=====:"+context.getJobDetail().getKey().getGroup());
        System.out.println("taskData=====:"+context.getJobDetail().getJobDataMap().get("taskData"));
        System.out.println(wechatRobotSyncFeignClient);
        //TODO  Feign注入，调用wechat-robot 服务
        String uuid = context.getJobDetail().getJobDataMap().getString("uuid");
        RestResultBuilder<Map<String, Object>> mapRestResultBuilder = wechatRobotSyncFeignClient.syncListener(uuid);
        Boolean listen = MapUtils.getBoolean(mapRestResultBuilder.getData(), "listen");
        log.info("正常访问微信的");
    }

}
