package com.xgg.auth.session.captcha;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author renchengwei
 * @date 2019-08-11
 * : TODO
 */
@Component
public class SessionCaptchaRepository<C extends CaptchaVO> implements CaptchaRepository {

    /** 操作session的工具类 */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /** 验证码放入session的时候前缀 */
    public final static String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    @Override
    public void save(ServletWebRequest request, CaptchaVO code, CaptchaTypeEnum captchaType) {
        sessionStrategy.setAttribute(request, getSessionKey(captchaType), code);
    }

    @Override
    public C get(ServletWebRequest request, CaptchaTypeEnum captchaType) {
        String sessionKey = getSessionKey(captchaType);
        // 拿到创建 create() 存储到session的code验证码对象
        return (C) sessionStrategy.getAttribute(request, sessionKey);
    }

    @Override
    public void remove(ServletWebRequest request, CaptchaTypeEnum captchaType) {
        sessionStrategy.removeAttribute(request, getSessionKey(captchaType));
    }

    /**
     * 构建验证码放入session时的key; 在保存的时候也使用该key
     * @param validateCodeType
     * @return
     */
    private String getSessionKey(CaptchaTypeEnum validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

}
