package com.xgg.auth.oauth2.captcha;

/**
 * 验证码库接口
 * @author renchengwei
 * @date 2019-08-11
 *
 */
public interface CaptchaRepository {
    /**
     * 保存验证码
     * @param captcha
     * @param captchaType
     */
    void save(Captcha captcha, CaptchaTypeEnum captchaType);

    /**
     *
     * 获取验证码
     * @author renchengwei
     * @date 2019-11-18
     * @param captchaToken 验证码Token
     * @param captchaType 验证码类型
     * @return Captcha 验证码对象
     * @throws
     *
     */
    Captcha get(String captchaToken,CaptchaTypeEnum captchaType);

    /**
     *
     * 移除验证码
     * @author renchengwei
     * @date 2019-11-18
     * @param captchaToken 验证码Token
     * @param captchaType 验证码类型
     * @return
     * @throws
     *
     */
    void remove(String captchaToken,CaptchaTypeEnum captchaType);
}
