package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import com.blog.cloud.service.IWechatRobotJobService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxw
 * @date 2019/2/13
 * @description
 */
@Slf4j
@RestController
@RequestMapping(value = "/wechat/robot")
@Api(value = "WechatRobotSyncController", description = "微信机器人任务控制器")
public class WechatRobotSyncController extends BaseController {

    @Autowired
    private IWechatRobotJobService wechatRobotJobService;

    /**
     * 创建cron任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @PostMapping(value = "/syncTaskJobCron")
    public String startCronJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        wechatRobotJobService.addCronJob(jobName,jobGroup);
        return "create cron task success";
    }

}
