package com.xgg.auth.oauth2.captcha.sms;

import com.xgg.auth.oauth2.captcha.CaptchaGenerate;
import com.xgg.auth.oauth2.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Resource;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
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