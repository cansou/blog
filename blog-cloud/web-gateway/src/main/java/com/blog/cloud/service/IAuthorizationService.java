package com.blog.cloud.service;

import com.blog.cloud.pojo.user.BlogUser;

public interface IAuthorizationService {

    BlogUser getManageAdminByUsername(String username);

}
