package com.xgg.auth.authentication.captcha.image;

import com.google.code.kaptcha.Producer;
import com.xgg.auth.authentication.captcha.CaptchaGenerate;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

/**
 * @author renchengwei
 * @date 2019-08-03
 * 图片验证码生成器
 */
public class ImageCaptchaGenerate implements CaptchaGenerate {

    @Autowired
    private Producer producer;

    @Override
    public ImageCaptcha generate() {
        String code = producer.createText();
        BufferedImage bufferedImage = producer.createImage(code);
        return new ImageCaptcha(bufferedImage, code, 60 * 5);
    }
}
