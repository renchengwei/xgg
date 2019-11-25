package com.xgg.auth.authentication.captcha;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码实体类，此类为验证码顶级类，包含了验证码的通用属性，个性化属性由子类实现
 * @author renchengwei
 * @date 2019-08-03
 */
@Data
public class Captcha implements Serializable {

    private static final long serialVersionUID = 4667369198044868126L;

    /**
     * 图形验证码token
     */
    private String captchaToken;
    /**
     * 验证码
     */
    private String code;
    /**
     * 失效时间 这个通常放在缓存中或维护在数据库中
     */
    private LocalDateTime expireTime;

    public Captcha(String code, int expireAfterSeconds) {
        this.code = code;
        //多少秒后
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public Captcha(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 是否失效
     *
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
