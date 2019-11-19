package com.xgg.auth.oauth2.captcha;

import com.xgg.auth.oauth2.support.strategy.IStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * 验证码处理器接口
 * @author renchengwei
 * @date 2019/8/5
 */
public interface CaptchaProcessor extends IStrategy<CaptchaTypeEnum> {

    /**
     * 创建验证码
     * @param request 封装请求和响应
     * @throws Exception
     * @return Captcha 验证码
     */
    Captcha create(ServletWebRequest request) throws IOException;

    Captcha getCaptcha(String captchaToken,CaptchaTypeEnum captchaTypeEnum);
    /**
     * 校验验证码
     * @param servletWebRequest
     * @param captchaTypeEnum
     * @throws CaptchaException 校验失败时
     */
    void validate(ServletWebRequest servletWebRequest, CaptchaTypeEnum captchaTypeEnum) throws CaptchaException;

    /**
    *  是否支持
    * @author  renchengwei
    * @date   2019-08-11
    * @param  captchaTypeEnum 验证码类型
    * @return 是否支持
    *
    */
    boolean support(CaptchaTypeEnum captchaTypeEnum);


}
