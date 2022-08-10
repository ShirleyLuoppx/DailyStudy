package com.gclibrary.http.exception;

/**
 * @author luojiang
 * @title 验证异常
 * @Date 2016-05-23 17:14
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
