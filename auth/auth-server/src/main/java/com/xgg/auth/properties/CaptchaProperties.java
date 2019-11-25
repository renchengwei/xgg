package com.xgg.auth.properties;

/**
 * 验证器配置主文件
 * @author renchengwei
 * @date 2019-08-04
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
