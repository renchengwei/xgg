package com.xgg.auth.session.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * @author renchengwei
 * @date 2019/8/5
 *  TODO
 */
@Service
@Slf4j
public class CaptchaCreateServiceImpl implements CaptchaCreateService {

    @Resource
    private CaptchaProcessorHolder captchaProcessorHolder;

    @Override
    public void createCaptcha(ServletWebRequest request, String type) {
        CaptchaTypeEnum captchaType = CaptchaTypeEnum.forCode(type);
        if(captchaType == null) {
            throw new CaptchaException("验证码类型不支持");
        }
        try {
            captchaProcessorHolder.findCaptchaProcessor(captchaType).create(request);
        } catch (Exception e) {
            log.info(e.getMessage(),e);
        }
    }
}
