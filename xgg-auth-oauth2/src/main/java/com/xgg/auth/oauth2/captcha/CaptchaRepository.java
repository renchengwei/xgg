package com.xgg.auth.oauth2.captcha;

/**
 * 验证码仓库接口
 * @author renchengwei
 * @date 2019-08-11
 * : TODO
 */
public interface CaptchaRepository {
    /**
     * 保存验证码
     * @param captcha
     * @param captchaType
     */
    void save(Captcha captcha, CaptchaTypeEnum captchaType);

    /**
     * 获取验证码
     * @return
     */
    Captcha get(String captchaToken,CaptchaTypeEnum captchaType);

    /**
     * 移除验证码
     */
    void remove(String captchaToken,CaptchaTypeEnum captchaType);
}
