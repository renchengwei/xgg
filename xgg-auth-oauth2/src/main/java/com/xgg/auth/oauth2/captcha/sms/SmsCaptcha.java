package com.xgg.auth.oauth2.captcha.sms;

import com.xgg.auth.oauth2.captcha.Captcha;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: renchengwei
 * @Date: 2019-08-18
 * @Description: TODO
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
