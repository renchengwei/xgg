package com.xgg.auth.session.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 自定义验证码异常
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }

    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
}
