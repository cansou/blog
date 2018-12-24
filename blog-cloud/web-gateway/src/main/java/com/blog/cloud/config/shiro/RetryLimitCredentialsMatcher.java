package com.blog.cloud.config.shiro;

import com.blog.cloud.service.IAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private IAuthorizationService authorizationService;

    private Cache<String, Object> lgoinRetryCache;

    public void setLgoinRetryCache(Cache<String, Object> lgoinRetryCache) {
        this.lgoinRetryCache = lgoinRetryCache;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws LockedAccountException {
        String username = (String) token.getPrincipal();
        int retryCount;
        Object ucount = lgoinRetryCache.get(username);
        if (ucount == null) {
            retryCount = 0;
        } else {
            retryCount = Integer.parseInt(lgoinRetryCache.get(username).toString());
        }
        if (retryCount > 5) {
            lgoinRetryCache.remove(username);
//            authorizationService.lockedManageAdminByUsername(username);
            log.warn("username: " + username + " tried to login more than 5 times in period");
            throw new LockedAccountException("username: " + username + " tried to login more than 5 times in period, and Locaed");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        lgoinRetryCache.put(username, ++retryCount);
        if (matches) {
            //clear retry data
            lgoinRetryCache.remove(username);
        }
        return matches;
    }

}
