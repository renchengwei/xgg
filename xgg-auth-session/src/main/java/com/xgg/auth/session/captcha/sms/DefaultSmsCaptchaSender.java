package com.xgg.auth.session.captcha.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author renchengwei
 * @date 2019-08-04
 * : TODO
 */
@Slf4j
public class DefaultSmsCaptchaSender implements SmsCaptchaSend {
    @Override
    public boolean sendSms(String mobile, String code) {
        log.info("模拟向手机{}发送短信验证码{}",mobile,code);
        log.info("短信渠道发送中...发送成功");
        return true;
    }
}
