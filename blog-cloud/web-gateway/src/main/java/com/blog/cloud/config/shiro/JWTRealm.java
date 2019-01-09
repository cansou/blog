package com.blog.cloud.config.shiro;

import com.blog.cloud.config.jwt.JWTCredentialsMatcher;
import com.blog.cloud.config.jwt.JWTToken;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import com.blog.cloud.utils.JWTUtil;
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
        //载入自定义的凭据验证类，进行AuthenticationInfo 验证
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 鉴权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //转换成JWTToken
        JWTToken jwtToken = (JWTToken) authenticationToken;
        String token = jwtToken.getToken();
        String username = JWTUtil.getUsername(token);
        BlogUser user = authorizationService.getBlogUserByUsername(JWTUtil.getUsername(token));
        if (user == null) {
            throw new AuthenticationException("用户异常，未查到用户信息，请检查是否存在" + username + "该用户");
        }
        //因为Shiro 没有实现JWTToken 的凭据验证，需要自己编写一个这样的类进行验证
        //生成凭据验证信息 使用Realm自定义的凭据验证类进行验证  JWTCredentialsMatcher.doCredentialsMatch() 进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return authenticationInfo;
    }
}
