package com.xgg.auth.session.captcha;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author renchengwei
 * @Date 2019/8/5
 * @Description TODO
 */
public interface CaptchaCreateService {

    /**
     *  生成验证码
     * @param request
     * @param type
     */
    void createCaptcha(ServletWebRequest request, String type);
}
