package com.blog.cloud.config.shiro;

import com.blog.cloud.config.jwt.JWTConfig;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //1.把AuthenticationToken转换为UsernamePasswordToken
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTConfig.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        BlogUser user = authorizationService.getBlogUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if(!JWTConfig.verify(token, username, user.getPassword())){
            throw new AuthenticationException("Username or password error");
        }

        /*new SimpleAuthenticationInfo(manageAdmin.getUsername(), manageAdmin.getPassword(),
                ByteSource.Util.bytes(manageAdmin.getSalt()), getName());*/

        return new SimpleAuthenticationInfo(token, token, "my_realm");

    }

}
