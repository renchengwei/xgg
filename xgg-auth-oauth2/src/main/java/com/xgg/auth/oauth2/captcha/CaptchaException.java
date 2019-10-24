package com.xgg.auth.oauth2.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 自定义验证码异常
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }

    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
}
