package com.xgg.auth.session.captcha.sms;

import com.xgg.auth.session.captcha.CaptchaGenerate;
import com.xgg.auth.session.captcha.CaptchaVO;
import com.xgg.auth.session.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
@Component("smsCaptchaGenerate")
public class SmsCaptchaGenerate implements CaptchaGenerate {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public CaptchaVO generate() {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCaptcha().getSms().getLength());
        return new CaptchaVO(code, securityProperties.getCaptcha().getSms().getExpireSeconds());
    }
}
