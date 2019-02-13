package com.blog.cloud.service.impl;

import com.blog.cloud.jobs.WechatRobotSyncTaskJob;
import com.blog.cloud.service.IWechatRobotJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@Service("wechatRobotJobService")
public class WechatRobotJobServiceImpl implements IWechatRobotJobService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void addCronJob(String jobName, String jobGroup) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobName + " 已存在");
            } else {
                //构建job信息
                jobDetail = JobBuilder.newJob(WechatRobotSyncTaskJob.class).withIdentity(jobName, jobGroup).build();
                //用JopDataMap来传递数据
                jobDetail.getJobDataMap().put("taskData", "hzb-cron-001");

                //表达式调度构建器(即任务执行的时间,每5秒执行一次)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAsyncJob(String jobName, String jobGroup) {

    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {

    }

    @Override
    public void resumeJob(String triggerName, String triggerGroup) {

    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {

    }

}
