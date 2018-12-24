package com.blog.cloud.service;

import com.blog.cloud.domain.user.BlogUserAddDto;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.pojo.user.BlogUser;

public interface IAuthorizationService {

    RestResultBuilder registerUser(BlogUserAddDto userAddDto);

    BlogUser getBlogUserByUsername(String username);

}
