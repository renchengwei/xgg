package com.xgg.auth.authentication.captcha.sms;

import com.xgg.auth.authentication.captcha.CaptchaGenerate;
import com.xgg.auth.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Resource;

/**
 * 短信验证码生成器
 * @author renchengwei
 * @date 2019-08-03
 */
public class SmsCaptchaGenerate implements CaptchaGenerate {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public SmsCaptcha generate() {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCaptcha().getSms().getLength());
        return new SmsCaptcha(code, securityProperties.getCaptcha().getSms().getExpireSeconds());
    }
}
