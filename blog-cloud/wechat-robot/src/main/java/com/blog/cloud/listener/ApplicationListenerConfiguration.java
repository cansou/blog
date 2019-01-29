package com.blog.cloud.listener;

import com.blog.cloud.event.WechatRobotApplicationEvent;
import com.blog.cloud.service.SyncServie;
import com.blog.cloud.tasks.WechatSyncServiceTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class ApplicationListenerConfiguration {

    @Autowired
    private WechatSyncServiceTask syncServiceTask;

    @EventListener
    public void wechatRobotApplicationListener (WechatRobotApplicationEvent wechatEvent) {
        wechatEvent.invokeEvent(syncServiceTask);
    }

}
