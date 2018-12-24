package com.blog.cloud.config.shiro;

import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IAuthorizationService authorizationService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        log.info("登录用户： " + username);

        BlogUser user = authorizationService.getBlogUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
//        AuthenticationInfo info = new SimpleAuthenticationInfo(token, token, "my_realm");
        AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
        return info;

    }

}
