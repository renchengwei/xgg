package com.xgg.auth.session.captcha.image;

import com.xgg.auth.session.captcha.AbstractCaptchaProcessor;
import com.xgg.auth.session.captcha.CaptchaGenerate;
import com.xgg.auth.session.captcha.CaptchaTypeEnum;
import com.xgg.auth.session.captcha.CaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renchengwei
 * @date 2019/8/5
 *  TODO
 */
@Slf4j
@Component
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor<ImageCaptchaVO> {
    private static final String FORMAT_NAME = "JPEG";

    @Autowired
    private CaptchaGenerate imageCaptchaGenerate;

    @Override
    protected ImageCaptchaVO generateCaptcha(ServletWebRequest request) {
        return (ImageCaptchaVO) imageCaptchaGenerate.generate();
    }

    @Override
    protected void send(ServletWebRequest request, ImageCaptchaVO captcha) throws IOException {
        HttpServletResponse response=request.getResponse();
        // 没有缓存
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(captcha.getImage(), FORMAT_NAME, response.getOutputStream());

    }

    @Override
    protected void save(ServletWebRequest request, ImageCaptchaVO captcha) {
        CaptchaVO captchaVo = new CaptchaVO(captcha.getCode(),captcha.getExpireTime());
        captchaRepository.save(request,captchaVo,getCondition());
    }

    @Override
    protected void check(ServletWebRequest request, CaptchaVO captcha) {

    }

    @Override
    public CaptchaTypeEnum getCondition() {
        return CaptchaTypeEnum.IMAGE;
    }
}
