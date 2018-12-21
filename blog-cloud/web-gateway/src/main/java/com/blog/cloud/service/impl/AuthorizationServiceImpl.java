package com.blog.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("authorizationService")
public class AuthorizationServiceImpl implements IAuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public BlogUser getManageAdminByUsername(String username){

        JSONObject result = restTemplate.postForObject("http://user-server/api/manage/manageAdmin/"+username, null, JSONObject.class);
        return result.getObject("blogUser", BlogUser.class);
    }

    public void lockedManageAdminByUsername(String username){
//        JSONObject result = restTemplate.postForObject("http://manage-server/api/manage/lockedManageAdmin/"+username, null, JSONObject.class);
    }

}
