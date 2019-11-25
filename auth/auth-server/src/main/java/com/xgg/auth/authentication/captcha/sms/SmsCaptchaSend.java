package com.xgg.auth.authentication.captcha.sms;

/**
 * 短信验证码发送者
 * @author renchengwei
 * @date 2019-08-03
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
