package com.blog.cloud.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class WechatRobotApplicationEvent extends ApplicationEvent {

    private String uuid;

    public WechatRobotApplicationEvent(String uuid) {
        super(uuid);
        this.uuid = uuid;
    }

    public void invokeEvent() {
        //执行异步监听操作

    }
}
