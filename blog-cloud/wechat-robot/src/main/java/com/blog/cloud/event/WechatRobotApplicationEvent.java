package com.blog.cloud.event;

import com.blog.cloud.config.WechatApiProperties;
import com.blog.cloud.service.SyncServie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class WechatRobotApplicationEvent extends ApplicationEvent {

    public WechatRobotApplicationEvent(Object source) {
        super(source);
    }

    public void invokeEvent(SyncServie syncServie) {
        log.info("接收到事件：" + syncServie.getClass());
        //执行异步监听操作
    }
}
