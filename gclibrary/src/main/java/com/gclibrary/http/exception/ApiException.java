package com.gclibrary.http.exception;

/**
 * @Title 接口请求异常
 * @Author luojiang
 * @Date 2016-05-23 17:14
 */
public class ApiException extends RuntimeException {
    private String code;// 0代表错误

    public ApiException(String code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

