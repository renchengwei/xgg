package com.xgg.auth.session.properties;

/**
 * @Author: renchengwei
 * @Date: 2019-08-04
 * @Description: TODO
 */
public class CaptchaProperties {

    private ImageCaptchaProperties image = new ImageCaptchaProperties();
    private SmsCaptchaProperties sms = new SmsCaptchaProperties();

    public ImageCaptchaProperties getImage() {
        return image;
    }

    public void setImage(ImageCaptchaProperties image) {
        this.image = image;
    }

    public SmsCaptchaProperties getSms() {
        return sms;
    }

    public void setSms(SmsCaptchaProperties sms) {
        this.sms = sms;
    }
}
