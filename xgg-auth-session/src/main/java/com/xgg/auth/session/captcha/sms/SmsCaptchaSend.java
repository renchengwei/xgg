package com.xgg.auth.session.captcha.sms;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : TODO
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
