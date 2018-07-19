package com.blog.cloud.http;

public class RestResultBuilder<T> {

    private int errCode;
    private String errMsg;
    private T data;

    public RestResultBuilder(int errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public RestResultBuilder(int errCode, String errMsg, T data) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public RestResultBuilder builder(){
        return null;
    }

}
