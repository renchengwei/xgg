package com.xgg.auth.oauth2.captcha;

import com.xgg.auth.oauth2.support.strategy.IStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author renchengwei
 * @Date 2019/8/5
 * @Description TODO
 */
public interface CaptchaProcessor extends IStrategy<CaptchaTypeEnum> {
    /**
     * 验证码
     */
    String CAPTCHA_SESSION_KEY = "captcha_session_key_";
    /**
     * 创建验证码
     * @param request 封装请求和响应
     * @throws Exception
     */
    void create(ServletWebRequest request) throws  Exception;
    /**
     * 校验验证码
     * @param servletWebRequest
     * @param captchaTypeEnum
     */
    void validate(ServletWebRequest servletWebRequest, CaptchaTypeEnum captchaTypeEnum) throws CaptchaException;

    /**
    * @Description 是否支持
    * @Author  renchengwei
    * @Date   2019-08-11
    * @param  captchaTypeEnum 验证码类型
    * @Return 是否支持
    *
    */
    boolean support(CaptchaTypeEnum captchaTypeEnum);
}
