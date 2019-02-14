package com.blog.cloud.service;

import com.blog.cloud.domain.task.WechatRobotSyncTaskDto;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
public interface IWechatRobotJobService {

    /**
     * 添加一个定时任务
     * @param jobName
     * @param jobGroup
     */
    void addCronJob(String jobName, String jobGroup);

    /**
     * 添加微信登录后定时任务
     * @param jobName
     * @param jobGroup
     */
    void createSyncTaskJobCron(WechatRobotSyncTaskDto dto);

    /**
     * 添加异步任务
     * @param jobName
     * @param jobGroup
     */
    void addAsyncJob(String jobName, String jobGroup);

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复任务
     * @param triggerName
     * @param triggerGroup
     */
    void resumeJob(String triggerName, String triggerGroup);

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);

}
