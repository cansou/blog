package com.blog.cloud.http;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResultBuilder<T> {

    private int resultCode;
    private String resultMsg;
    private T data;

    public RestResultBuilder() {
    }

    public RestResultBuilder(int resultCode, String resultMsg) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public RestResultBuilder(int resultCode, String resultMsg, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

}
