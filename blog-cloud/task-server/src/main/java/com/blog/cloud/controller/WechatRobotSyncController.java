package com.blog.cloud.controller;

import com.blog.cloud.common.BaseController;
import com.blog.cloud.domain.task.WechatRobotSyncTaskDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.service.IWechatRobotJobService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 创建微信定时任务
     * @param dto
     * @return
     */
    @PostMapping(value = "/syncTaskJobCron")
    public RestResultBuilder syncTaskJobCron(@RequestBody WechatRobotSyncTaskDto dto){
        wechatRobotJobService.createSyncTaskJobCron(dto);
        return successBuild();
    }

}
