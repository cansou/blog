package com.blog.cloud.service.impl;

import com.blog.cloud.domain.task.WechatRobotSyncTaskDto;
import com.blog.cloud.jobs.WechatRobotSyncQuartzJobBean;
import com.blog.cloud.service.IWechatRobotJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@Slf4j
@Service("wechatRobotJobService")
public class WechatRobotJobServiceImpl implements IWechatRobotJobService {

    @Autowired
    private Scheduler scheduler;

    public void createSyncTaskJobCron(WechatRobotSyncTaskDto dto) {
        try {
            String jobName = dto.getUni();
            String jobGroup = dto.getUni();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobName + " 已存在");
            } else {
                //构建job信息
                jobDetail = JobBuilder.newJob(WechatRobotSyncQuartzJobBean.class).withIdentity(jobName, jobGroup).build();
                //用JopDataMap来传递数据
                jobDetail.getJobDataMap().put("uuid", dto.getUuid());

                //表达式调度构建器(即任务执行的时间,每5秒执行一次)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/3 * * * * ?");

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildSyncRemindTimer(WechatRobotSyncTaskDto dto) {
        try {
            String jobName = dto.getUni();
            String jobGroup = dto.getUni();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                log.info("job:" + jobName + " 已存在");
            } else {
                //构建job信息
                jobDetail = JobBuilder.newJob(WechatRobotSyncQuartzJobBean.class).withIdentity(jobName, jobGroup).build();
                //用JopDataMap来传递数据
                jobDetail.getJobDataMap().put("uuid", dto.getUuid());

                //按新的cronExpression表达式构建一个新的trigger
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).startNow().build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException e) {
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
        try {
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
