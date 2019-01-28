package com.blog.cloud.tasks;

import com.blog.cloud.service.SyncServie;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Data
//@Component
public class WechatSyncServiceTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    private Boolean flag = false;

    @Autowired
    private SyncServie syncServie;



    /**
     * 固定时间，从服务启动后每隔3秒执行定时任务
     */
    @Scheduled(fixedRate = 3000)
    public void invokeSyncServie() {
        log.info("定时执行响应任务，每3秒钟请求一次");
        if (flag) {
            syncServie.listen();
        }
    }

}
