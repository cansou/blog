package com.cloud.framework.uitl;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lxw
 * @date 2018/12/31
 * @description
 */
@ConditionalOnBean(value = RedissonClient.class)
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    public <T extends Serializable> void set (String key, T t) {
        if (t instanceof String) {
            stringRedisTemplate.opsForValue().set(key, (String) t);
        } else {
            redisCacheTemplate.opsForValue().set(key, t);
        }
    }

    public <T> T get (String key, Class<T> clazz) {
        if (String.class == clazz) {
            return clazz.cast(stringRedisTemplate.opsForValue().get(key));
        } else {
            return clazz.cast(redisCacheTemplate.opsForValue().get(key));
        }
    }

}
