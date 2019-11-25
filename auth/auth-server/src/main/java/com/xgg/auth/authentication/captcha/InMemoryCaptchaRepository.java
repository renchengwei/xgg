package com.xgg.auth.authentication.captcha;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于内存的验证码库
 * @author renchengwei
 * @date 2019-11-18
 */
public class InMemoryCaptchaRepository<C extends Captcha> implements CaptchaRepository {

    private Map<String, Captcha> inMemoryStrategy = new HashMap<>();

    @Override
    public void save(Captcha code, CaptchaTypeEnum captchaType) {
        inMemoryStrategy.put(getInMemoryKey(code.getCaptchaToken(),captchaType),code);
    }

    @Override
    public C get(String captchaToken,CaptchaTypeEnum captchaType) {
        return (C) inMemoryStrategy.get(getInMemoryKey(captchaToken,captchaType));
    }

    @Override
    public void remove(String captchaToken,CaptchaTypeEnum captchaType) {
        inMemoryStrategy.remove(getInMemoryKey(captchaToken,captchaType));
    }

    private String getInMemoryKey(String captchaToken,CaptchaTypeEnum captchaType) {
        return captchaType.getCode() + ":" + captchaToken;
    }
}
