package com.xgg.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * 自定配置核心配置
 * @author renchengwei
 * @date 2019-08-03
 *
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
