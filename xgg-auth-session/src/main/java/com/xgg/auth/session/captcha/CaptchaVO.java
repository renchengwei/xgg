package com.xgg.auth.session.captcha;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
@Data
public class CaptchaVO  implements Serializable {

    private static final long serialVersionUID = 4667369198044868126L;
    /**
     * 验证码
     */
    private String code;
    /**
     * 失效时间 这个通常放在缓存中或维护在数据库中
     */
    private LocalDateTime expireTime;

    public CaptchaVO(String code, int expireAfterSeconds) {
        this.code = code;
        //多少秒后
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public CaptchaVO(String code, LocalDateTime expireTime) {
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
