package com.xgg.auth.oauth2.captcha.sms;

import com.xgg.auth.oauth2.captcha.CaptchaVO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: renchengwei
 * @Date: 2019-08-18
 * @Description: TODO
 */
@Data
public class SmsCaptchaVO extends CaptchaVO {

    private String mobile;

    public SmsCaptchaVO(String code, int expireAfterSeconds) {
        super(code, expireAfterSeconds);
    }

    public SmsCaptchaVO(String code, LocalDateTime expireAfterSeconds, String mobile) {
        super(code, expireAfterSeconds);
        this.mobile = mobile;
    }
}
