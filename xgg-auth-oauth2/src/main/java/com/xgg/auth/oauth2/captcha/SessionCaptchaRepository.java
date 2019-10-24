package com.xgg.auth.oauth2.captcha;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
@Component
public class SessionCaptchaRepository<C extends CaptchaVO> implements CaptchaRepository {

    /** 操作session的工具类 */
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Map<String,CaptchaVO> sessionStrategy = new HashMap<>();
    /** 验证码放入session的时候前缀 */
    public final static String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    @Override
    public void save(ServletWebRequest request, CaptchaVO code, CaptchaTypeEnum captchaType) {
        sessionStrategy.put(getSessionKey(captchaType), code);
    }

    @Override
    public C get(ServletWebRequest request, CaptchaTypeEnum captchaType) {
        String sessionKey = getSessionKey(captchaType);
        // 拿到创建 create() 存储到session的code验证码对象
        return (C) sessionStrategy.get(sessionKey);
    }

    @Override
    public void remove(ServletWebRequest request, CaptchaTypeEnum captchaType) {
        sessionStrategy.remove(getSessionKey(captchaType));
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
