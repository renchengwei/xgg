package com.xgg.auth.authentication.captcha.image;

import com.alibaba.fastjson.JSON;
import com.xgg.auth.authentication.captcha.*;
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
import java.util.UUID;

/**
 * @author renchengwei
 * @date 2019/8/5
 * 图片验证码处理器
 */
@Slf4j
@Component
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor<ImageCaptcha> {

    @Autowired
    private CaptchaGenerate imageCaptchaGenerate;

    @Override
    protected ImageCaptcha generateCaptcha(ServletWebRequest request) {
        return (ImageCaptcha) imageCaptchaGenerate.generate();
    }

    @Override
    protected String getCaptchaTokenForServletRequest(ServletWebRequest request) {
        String token;
        try {
            token = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "imageToken");
        } catch (ServletRequestBindingException e) {
            throw new CaptchaException("获取imageToken失败");
        }

        token = token.trim();

        if(StringUtils.isBlank(token)) {
            throw new CaptchaException("imageToken不能为空");
        }
        return token;
    }

    @Override
    protected void send(ServletWebRequest request, ImageCaptcha captcha) throws IOException {
        CaptchaVO captchaVO = new CaptchaVO(captcha.getCaptchaToken(),captcha.getExpireTime());
        HttpServletResponse response = request.getResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(captchaVO));
    }

    @Override
    protected void save(ImageCaptcha captcha,ServletWebRequest request) {
        captcha.setCaptchaToken(UUID.randomUUID().toString());
        captchaRepository.save(captcha,getCondition());
    }

    @Override
    protected void check(ServletWebRequest request, Captcha captcha) {

    }

    @Override
    public CaptchaTypeEnum getCondition() {
        return CaptchaTypeEnum.IMAGE;
    }
}
