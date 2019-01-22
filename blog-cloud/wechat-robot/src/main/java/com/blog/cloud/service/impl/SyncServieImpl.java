package com.blog.cloud.service.impl;

import com.blog.cloud.config.CacheConfiguration;
import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.response.SyncCheckResponse;
import com.blog.cloud.enums.RetCode;
import com.blog.cloud.enums.Selector;
import com.blog.cloud.service.SyncServie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxw
 * @date 2019/1/22
 * @description
 */
@Slf4j
@Service
public class SyncServieImpl implements SyncServie {

    @Autowired
    private WechatApiServiceInternal internal;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    public void listen() {

        try {

            SyncCheckResponse syncCheckResponse = internal.syncCheck(cacheConfiguration.getSyncUrl(), cacheConfiguration.getToken(), cacheConfiguration.getSyncKey());
            Integer retCode = syncCheckResponse.getRetcode();
            Integer selector = syncCheckResponse.getSelector();

            //log.info(String.format("[SYNCCHECK] retcode = %s, selector = %s", retCode, selector));

            if (retCode == RetCode.NORMAL.getCode()) {
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");
                log.info("你有一条新的消息");

                if (selector == Selector.NEW_MESSAGE.getCode()) {
                    onNewMessage();
                } else if (selector == Selector.ENTER_LEAVE_CHAT.getCode()) {
                    sync();
                } else if (selector == Selector.CONTACT_UPDATED.getCode()) {
                    sync();
                } else if (selector == Selector.UNKNOWN1.getCode()) {
                    sync();
                } else if (selector == Selector.UNKNOWN6.getCode()) {
                    sync();
                } else if (selector != Selector.NORMAL.getCode()) {
                    throw new RuntimeException("syncCheckResponse ret = " + retCode);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    void onNewMessage() {

    }

    void sync() {

    }

}
