package com.blog.cloud.common;

import com.blog.cloud.http.RestResultBuilder;

public class BaseController {

    public <T> RestResultBuilder successBuild(T t) {

        return new RestResultBuilder<T>(200, "访问成功", t);
    }

}
