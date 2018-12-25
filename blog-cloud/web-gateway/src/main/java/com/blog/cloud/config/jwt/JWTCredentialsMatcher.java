package com.blog.cloud.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.blog.cloud.pojo.user.BlogUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

@Slf4j
public class JWTCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) authenticationToken.getCredentials();
        Object stored = authenticationInfo.getCredentials();
        String password = stored.toString();

        BlogUser user = (BlogUser) authenticationInfo.getPrincipals().getPrimaryPrincipal();
        try {
            JWTUtil.verify(token, user.getUsername(), password);
            return true;
        } catch (JWTVerificationException e) {
            log.error("Token Error:{}", e.getMessage());
        }
        return false;
    }

}
