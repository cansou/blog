package com.blog.cloud.service;

import com.blog.cloud.domain.user.BlogUserAddDto;

public interface IUserService {

    Integer registerUser (BlogUserAddDto userAddDto);

}
