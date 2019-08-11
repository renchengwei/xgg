package com.xgg.auth.session.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 自定配置核心配置
 */
@Component
@ConfigurationProperties(value = "xgg.auth",ignoreInvalidFields = true)
public class SecurityProperties {

    private SessionProperties session = new SessionProperties();
    private CaptchaProperties captcha = new CaptchaProperties();

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public CaptchaProperties getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaProperties captcha) {
        this.captcha = captcha;
    }
}
