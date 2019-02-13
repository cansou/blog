package com.blog.cloud.jobs;

import com.blog.cloud.feign.wechat.robot.IWechatRobotSyncFeignClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@Component
public class WechatRobotSyncTaskJob implements Job {

    @Autowired
    private IWechatRobotSyncFeignClient wechatRobotSyncFeignClient;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("=========================定时任务每5秒执行一次===============================");
        System.out.println("jobName=====:"+jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println("jobGroup=====:"+jobExecutionContext.getJobDetail().getKey().getGroup());
        System.out.println("taskData=====:"+jobExecutionContext.getJobDetail().getJobDataMap().get("taskData"));
        System.out.println(wechatRobotSyncFeignClient);
        //TODO  Feign注入，调用wechat-robot 服务
    }

}
