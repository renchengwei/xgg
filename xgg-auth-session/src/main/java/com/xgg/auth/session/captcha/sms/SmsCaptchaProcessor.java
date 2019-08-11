package com.xgg.auth.session.captcha.sms;

import com.xgg.auth.session.SecurityConstants;
import com.xgg.auth.session.captcha.AbstractCaptchaProcessor;
import com.xgg.auth.session.captcha.CaptchaGenerate;
import com.xgg.auth.session.captcha.CaptchaTypeEnum;
import com.xgg.auth.session.captcha.CaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * @Author renchengwei
 * @Date 2019/8/5
 * @Description TODO
 */
@Slf4j
@Component
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor<CaptchaVO> {
    @Resource
    private CaptchaGenerate smsCaptchaGenerate;
    @Resource
    private SmsCaptchaSend captchaSend;

    @Override
    protected CaptchaVO generateCaptcha(ServletWebRequest request) {
        return smsCaptchaGenerate.generate();
    }

    @Override
    protected void send(ServletWebRequest request, CaptchaVO captcha) throws ServletRequestBindingException {
        String mobile= ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        captchaSend.sendSms(mobile, captcha.getCode());
    }

    @Override
    public CaptchaTypeEnum getCondition() {
        return CaptchaTypeEnum.SMS;
    }
}
