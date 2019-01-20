package com.blog.cloud.scheduler;

import com.blog.cloud.config.WxMpConfiguration;
import com.blog.cloud.constants.ConfigKeys;
import com.blog.cloud.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lxw
 * @date 2019/1/10
 * @description
 */
@Slf4j
@Component
public class WxMpRefreshAccessTokenScheduler {

    @Autowired
    private RedisUtil redisUtil;

    //@Scheduled(cron = "0 0/1 * * * ?")
    /**
     * 每小时刷新一次AccessToken
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void refreshAccessTokenRun() {
        List<WxMpService> mpServices = WxMpConfiguration.getMpServices();
        mpServices.stream().forEach(mp -> {
            try {
                Integer appIdIndex = mpServices.indexOf(mp);
                String accessToken = mp.getAccessToken(true);
                redisUtil.set(ConfigKeys.accessToken + "_" + appIdIndex, accessToken);
                log.info("刷新成功");
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        });
    }
}
