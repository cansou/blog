package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import com.blog.cloud.service.IJobService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/job")
@Api(value = "JobController", description = "任务控制器")
public class JobController extends BaseController {

    @Autowired
    private IJobService jobService;

    /**
     * 创建cron任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/cron",method = RequestMethod.POST)
    public String startCronJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup){
        jobService.addCronJob(jobName,jobGroup);
        return "create cron task success";
    }

}
