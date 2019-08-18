package com.xgg.auth.session.captcha.sms;

import com.xgg.auth.session.SecurityConstants;
import com.xgg.auth.session.captcha.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor<SmsCaptchaVO> {
    @Resource
    private CaptchaGenerate smsCaptchaGenerate;
    @Resource
    private SmsCaptchaSend captchaSend;

    @Override
    protected SmsCaptchaVO generateCaptcha(ServletWebRequest request) {
        return (SmsCaptchaVO) smsCaptchaGenerate.generate();
    }

    @Override
    protected void send(ServletWebRequest request, SmsCaptchaVO captcha) {
        captchaSend.sendSms(captcha.getMobile(), captcha.getCode());
    }

    @Override
    protected void save(ServletWebRequest request, SmsCaptchaVO captcha) {
        String mobile = getMobileForServletReques(request);
        captcha.setMobile(mobile);
        captchaRepository.save(request,captcha,getCondition());
    }

    @Override
    protected void check(ServletWebRequest request, CaptchaVO captcha) {
        String mobile = getMobileForServletReques(request);
        SmsCaptchaVO smsCaptchaVO = (SmsCaptchaVO) captchaRepository.get(request,getCondition());
        if(!smsCaptchaVO.getMobile().equals(mobile)) {
            throw new CaptchaException(getCondition() + "验证码不匹配");
        }
    }

    @Override
    public CaptchaTypeEnum getCondition() {
        return CaptchaTypeEnum.SMS;
    }

    private String getMobileForServletReques(ServletWebRequest request) {
        String mobile;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        } catch (ServletRequestBindingException e) {
            throw new CaptchaException("获取手机号码失败");
        }

        mobile = mobile.trim();

        if(StringUtils.isBlank(mobile)) {
            throw new CaptchaException("手机号码不能为空");
        }

        return mobile;
    }
}
