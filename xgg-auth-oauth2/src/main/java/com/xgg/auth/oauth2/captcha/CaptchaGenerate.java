package com.xgg.auth.oauth2.captcha;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 验证码生成接口
 */
public interface CaptchaGenerate {
    /**
     * 生成验证码
     *
     * @return
     */
    Captcha generate();
}
