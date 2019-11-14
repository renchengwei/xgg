package com.xgg.auth.oauth2.captcha;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
@Component
public class SessionCaptchaRepository<C extends Captcha> implements CaptchaRepository {

    private Map<String, Captcha> inMemoryStrategy = new HashMap<>();

    @Override
    public void save(Captcha code, CaptchaTypeEnum captchaType) {
        inMemoryStrategy.put(getinMemoryKey(code.getCaptchaToken(),captchaType),code);
    }

    @Override
    public C get(String captchaToken,CaptchaTypeEnum captchaType) {
        return (C) inMemoryStrategy.get(getinMemoryKey(captchaToken,captchaType));
    }

    @Override
    public void remove(String captchaToken,CaptchaTypeEnum captchaType) {
        inMemoryStrategy.remove(getinMemoryKey(captchaToken,captchaType));
    }

    private String getinMemoryKey(String captchaToken,CaptchaTypeEnum captchaType) {
        return captchaType.getCode() + ":" + captchaToken;
    }
}
