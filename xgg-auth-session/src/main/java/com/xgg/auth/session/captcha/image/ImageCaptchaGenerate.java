package com.xgg.auth.session.captcha.image;

import com.google.code.kaptcha.Producer;
import com.xgg.auth.session.captcha.CaptchaGenerate;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 图片验证码生成器
 */
public class ImageCaptchaGenerate implements CaptchaGenerate {

    @Autowired
    private Producer producer;

    @Override
    public ImageCaptchaVO generate() {
        String code = producer.createText();
        BufferedImage bufferedImage = producer.createImage(code);
        return new ImageCaptchaVO(bufferedImage, code, 60 * 5);
    }
}
