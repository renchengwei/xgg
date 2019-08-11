package com.xgg.auth.session.captcha.image;

import com.xgg.auth.session.captcha.CaptchaVO;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author: renchengwei
 * @Date: 2019-07-31
 * @Description: TODO
 */
@Data
public class ImageCaptchaVO extends CaptchaVO {

    private BufferedImage image;

    public ImageCaptchaVO(BufferedImage image, String code, int expireAfterSeconds){
        super(code,expireAfterSeconds);
        this.image = image;
    }

    public ImageCaptchaVO(BufferedImage image, String code, LocalDateTime expireTime){
        super(code,expireTime);
        this.image = image;
    }

}
