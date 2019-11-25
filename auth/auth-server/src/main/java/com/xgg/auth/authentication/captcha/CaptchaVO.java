package com.xgg.auth.authentication.captcha;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CaptchaVO implements Serializable {

    public CaptchaVO(){}

    public CaptchaVO(String captchaToken, LocalDateTime expireTime) {
        this.captchaToken = captchaToken;
        this.expireTime = expireTime;
    }

    /**
     * 图形验证码token
     */
    private String captchaToken;
    /**
     * 失效时间 这个通常放在缓存中或维护在数据库中
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
}
