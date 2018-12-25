package com.blog.cloud.config.shiro;

import com.blog.cloud.config.jwt.JWTUtil;
import com.blog.cloud.config.jwt.JWTCredentialsMatcher;
import com.blog.cloud.config.jwt.JWTToken;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private IAuthorizationService authorizationService;

    public JWTRealm() {
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String token = jwtToken.getToken();
        String username = JWTUtil.getUsername(token);
        BlogUser user = authorizationService.getBlogUserByUsername(JWTUtil.getUsername(token));
        if (user == null) {
            throw new AuthenticationException("用户异常，未查到用户信息，请检查是否存在" + username + "该用户");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return authenticationInfo;
    }
}
