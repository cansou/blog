package com.blog.cloud.event;

import com.blog.cloud.service.SyncServie;
import com.blog.cloud.tasks.WechatSyncServiceTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class WechatRobotApplicationEvent extends ApplicationEvent {

    public WechatRobotApplicationEvent(Object source) {
        super(source);
    }

    public void invokeEvent(WechatSyncServiceTask syncServiceTask) {
        //执行异步监听操作
        syncServiceTask.setFlag(true);
        syncServiceTask.invokeSyncServie();
    }
}