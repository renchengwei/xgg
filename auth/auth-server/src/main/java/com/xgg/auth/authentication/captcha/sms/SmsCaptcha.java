package com.xgg.auth.authentication.captcha.sms;

import com.xgg.auth.authentication.captcha.Captcha;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短信验证码对象
 * @author renchengwei
 * @date 2019-08-18
 */
@Data
public class SmsCaptcha extends Captcha {

    private String mobile;

    public SmsCaptcha(String code, int expireAfterSeconds) {
        super(code, expireAfterSeconds);
    }

    public SmsCaptcha(String code, LocalDateTime expireAfterSeconds, String mobile) {
        super(code, expireAfterSeconds);
        this.mobile = mobile;
    }
}
