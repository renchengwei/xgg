package com.xgg.auth.oauth2.captcha.image;

import com.xgg.auth.oauth2.captcha.Captcha;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author: renchengwei
 * @Date: 2019-07-31
 * @Description: TODO
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
