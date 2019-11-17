package com.xgg.auth.session.captcha.sms;

import com.xgg.auth.session.captcha.CaptchaVO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author renchengwei
 * @date 2019-08-18
 * : TODO
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
