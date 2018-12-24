package com.blog.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blog.cloud.common.PasswordEncrypter;
import com.blog.cloud.domain.user.BlogUserAddDto;
import com.blog.cloud.feign.UserServerFeignClient;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("authorizationService")
public class AuthorizationServiceImpl implements IAuthorizationService {

    @Autowired
    private UserServerFeignClient userServerClient;

    @Override
    public RestResultBuilder registerUser(BlogUserAddDto userAddDto) {
        BlogUser user = new BlogUser();
        BeanUtils.copyProperties(userAddDto, user);
        PasswordEncrypter<BlogUser> encrypter = new PasswordEncrypter<>();
        encrypter.encryptPassword(user);
        return userServerClient.registerUser(user);
    }

    public BlogUser getBlogUserByUsername(String username) {

        return null;
    }

    public void lockedManageAdminByUsername(String username){
//        JSONObject result = restTemplate.postForObject("http://manage-server/api/manage/lockedManageAdmin/"+username, null, JSONObject.class);
    }

}
