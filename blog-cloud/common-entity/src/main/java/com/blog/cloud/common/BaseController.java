package com.blog.cloud.common;

import com.blog.cloud.constants.HttpResultConstant;
import com.blog.cloud.http.RestResultBuilder;

public class BaseController {

    /**
     * 成功有数据返回
     * @param t
     * @param <T>
     * @return
     */
    public <T> RestResultBuilder successBuild(T t) {
        return new RestResultBuilder<T>(HttpResultConstant.SUCCESS, HttpResultConstant.SUCCESS_MSG, t);
    }


    /**
     * 成功无数据返回
     * @return
     */
    public RestResultBuilder successBuild() {
        return this.successBuild(null);
    }

    /**
     * 自定义错误码，错误信息
     * @param code
     * @param msg
     * @return
     */
    public RestResultBuilder failBuild(Integer code, String msg) {
        return new RestResultBuilder(code, msg);
    }

    /**
     * 默认失败码
     * 失败自定义错误信息
     * @param msg
     * @return
     */
    public RestResultBuilder failBuild(String msg) {
        return this.failBuild(HttpResultConstant.ERROR, msg);
    }

    /**
     * 默认失败
     * @return
     */
    public RestResultBuilder failBuild() {
        return this.failBuild(HttpResultConstant.ERROR_MSG);
    }
}
