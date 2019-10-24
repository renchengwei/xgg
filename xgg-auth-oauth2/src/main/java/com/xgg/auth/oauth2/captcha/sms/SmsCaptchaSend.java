package com.xgg.auth.oauth2.captcha.sms;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
public interface SmsCaptchaSend {
    /**
     * 发送短信验证码
     * @param mobile
     * @param code
     * @return
     */
    boolean sendSms(String mobile, String code);

}
