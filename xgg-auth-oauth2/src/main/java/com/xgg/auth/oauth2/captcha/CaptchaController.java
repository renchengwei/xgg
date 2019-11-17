package com.xgg.auth.oauth2.captcha;

import com.xgg.auth.oauth2.captcha.image.ImageCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renchengwei
 * @date 2019-08-03
 * 验证码控制器
 */
@RestController
@Slf4j
public class CaptchaController {

    @Resource
    private CaptchaProcessorHolder captchaProcessorHolder;
    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @throws IOException
     * @return
     */
    @RequestMapping("/captcha/{type}")
    public void createKaptcha(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        CaptchaTypeEnum captchaType = CaptchaTypeEnum.forCode(type);
        if(captchaType == null) {
            throw new CaptchaException("验证码类型不支持");
        }
        captchaProcessorHolder.findCaptchaProcessor(captchaType).create(new ServletWebRequest(request, response));
    }

    @RequestMapping("/captcha/image/{captchaToken}")
    public void getImageKaptcha(HttpServletRequest request, HttpServletResponse response, @PathVariable("captchaToken") String captchaToken) throws IOException {
        ImageCaptcha imageCaptcha = (ImageCaptcha) captchaProcessorHolder.findCaptchaProcessor(CaptchaTypeEnum.IMAGE).getCaptcha(captchaToken,CaptchaTypeEnum.IMAGE);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(imageCaptcha.getImage(), "JPEG", response.getOutputStream());
    }

}
