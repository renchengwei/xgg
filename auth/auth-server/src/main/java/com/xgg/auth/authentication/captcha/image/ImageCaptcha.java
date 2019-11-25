package com.xgg.auth.authentication.captcha.image;

import com.xgg.auth.authentication.captcha.Captcha;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author renchengwei
 * @date 2019-07-31
 * 图片验证码
 */
@Data
public class ImageCaptcha extends Captcha {
    private BufferedImage image;
    private String imageToken;
    public ImageCaptcha(BufferedImage image, String code, int expireAfterSeconds){
        super(code,expireAfterSeconds);
        this.image = image;
    }
    public ImageCaptcha(BufferedImage image, String code, LocalDateTime expireTime){
        super(code,expireTime);
        this.image = image;
    }

}
