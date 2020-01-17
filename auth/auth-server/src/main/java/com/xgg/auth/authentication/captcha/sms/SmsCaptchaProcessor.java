package com.xgg.auth.authentication.captcha.sms;

import com.xgg.auth.authentication.captcha.*;
import com.xgg.auth.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 短信验证码处理器
 * @author renchengwei
 * @date 2019/8/5
 *
 */
@Slf4j
@Component
public class SmsCaptchaProcessor extends AbstractCaptchaProcessor<SmsCaptcha> {
    @Autowired
    private CaptchaGenerate smsCaptchaGenerate;
    @Autowired
    private SmsCaptchaSend captchaSend;

    @Override
    protected SmsCaptcha generateCaptcha(ServletWebRequest request) {
        return (SmsCaptcha) smsCaptchaGenerate.generate();
    }

    @Override
    protected String getCaptchaTokenForServletRequest(ServletWebRequest request) {
        String token;
        try {
            token = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        } catch (ServletRequestBindingException e) {
            throw new CaptchaException("获取mobile失败");
        }

        token = token.trim();

        if(StringUtils.isBlank(token)) {
            throw new CaptchaException("mobile不能为空");
        }
        return token;
    }

    @Override
    protected void save(SmsCaptcha captcha,ServletWebRequest request) {
        String mobile = getMobileForServletRequest(request);
        captcha.setMobile(mobile);
        captcha.setCaptchaToken(captcha.getMobile());
        captchaRepository.save(captcha,getCondition());
    }

    @Override
    protected void check(ServletWebRequest request, Captcha captcha) {
        SmsCaptcha smsCaptcha = (SmsCaptcha) captcha;
        String mobile = getMobileForServletRequest(request);
        if(!smsCaptcha.getMobile().equals(mobile)) {
            throw new CaptchaException(getCondition() + "验证码不匹配");
        }
    }

    @Override
    protected void send(ServletWebRequest request, SmsCaptcha captcha) throws IOException {
        captchaSend.sendSms(captcha.getMobile(), captcha.getCode());
        HttpServletResponse response = request.getResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JacksonUtils.obj2json("短信已发送，请注意查收"));
    }

    @Override
    public CaptchaTypeEnum getCondition() {
        return CaptchaTypeEnum.SMS;
    }

    private String getMobileForServletRequest(ServletWebRequest request) {
        String mobile;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
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
