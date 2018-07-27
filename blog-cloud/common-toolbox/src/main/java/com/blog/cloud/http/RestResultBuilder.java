package com.blog.cloud.http;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResultBuilder<T> {

    private int errCode;
    private String errMsg;
    private T data;

    public RestResultBuilder() {
    }

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

}
