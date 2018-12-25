package com.blog.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.blog.cloud.common.BaseController;
import com.blog.cloud.config.jwt.JWTUtil;
import com.blog.cloud.domain.user.BlogUserAddDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/manage/user")
@RestController
@Api(value = "ManageUserController", description = "管理用户接口")
public class ManageUserController extends BaseController {

    @Autowired
    private IAuthorizationService authorizationService;

    /**
     * 用户注册
     *
     * @param userAddDto 用户注册对象
     * @return
     */
    @PostMapping(value = "/registerUser")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public RestResultBuilder registerUser(@RequestBody BlogUserAddDto userAddDto){
        return authorizationService.registerUser(userAddDto);
    }

    /**
     * 用户登陆
     *
     * @param userAddDto 用户登陆
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public RestResultBuilder login(@RequestBody BlogUserAddDto userAddDto){

        String username = userAddDto.getUsername();
        String password = userAddDto.getPassword();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        String errmsg = "";
        try {
            log.info("对用户[" + username + "]进行登录验证..验证开始");
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            errmsg = "对用户[" + username + "]进行登录验证..验证未通过,未知账户";
        } catch (LockedAccountException lae) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多,账户已锁定");
            errmsg = "对用户[" + username + "]进行登录验证..验证未通过,错误次数过多,账户已锁定";
        } catch (AuthenticationException ae) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            errmsg = "对用户[" + username + "]进行登录验证..验证未通过,账户已锁定,请通知超级管理员解锁";
        }
        if (subject.isAuthenticated()) {
            errmsg = "用户[" + username + "]进行登录验证..验证通过";
            BlogUser admin = authorizationService.getBlogUserByUsername(username);
                    // Create auth token
            String jwt = JWTUtil.createToken(admin.getUsername(), admin.getPassword());
            JSONObject jsonObject = new JSONObject();
            if (jwt != null) {
                jsonObject.put("token", jwt);
                jsonObject.put("manageAdmin", admin);
            }
            return successBuild(jsonObject);
        }
        usernamePasswordToken.clear();
        return new RestResultBuilder(990001, errmsg);
    }

}
