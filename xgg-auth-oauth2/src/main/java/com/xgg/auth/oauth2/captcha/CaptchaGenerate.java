package com.xgg.auth.oauth2.captcha;

/**
 * @author renchengwei
 * @date 2019-08-03
 * 验证码生成接口
 */
public interface CaptchaGenerate {
    /**
     * 生成验证码
     *
     * @return
     */
    Captcha generate();
}
