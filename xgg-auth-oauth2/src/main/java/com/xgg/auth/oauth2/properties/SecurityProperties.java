package com.xgg.auth.oauth2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 自定配置核心配置
 */
@Component
@ConfigurationProperties(value = "xgg.auth",ignoreInvalidFields = true)
public class SecurityProperties {

    private CaptchaProperties captcha = new CaptchaProperties();

    public CaptchaProperties getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaProperties captcha) {
        this.captcha = captcha;
    }
}
