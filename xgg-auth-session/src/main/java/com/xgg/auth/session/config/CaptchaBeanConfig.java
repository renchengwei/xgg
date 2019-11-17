package com.xgg.auth.session.config;

import com.xgg.auth.session.captcha.CaptchaGenerate;
import com.xgg.auth.session.captcha.image.ImageCaptchaGenerate;
import com.xgg.auth.session.captcha.sms.DefaultSmsCaptchaSender;
import com.xgg.auth.session.captcha.sms.SmsCaptchaGenerate;
import com.xgg.auth.session.captcha.sms.SmsCaptchaSend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 验证码Bean生成配置类
 */
@Configuration
public class CaptchaBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCaptchaGenerate")
    public CaptchaGenerate imageCaptchaGenerate() {
        ImageCaptchaGenerate imageCaptchaGenerate = new ImageCaptchaGenerate();
        return imageCaptchaGenerate;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCaptchaGenerate")
    public CaptchaGenerate smsCaptchaGenerate() {
        SmsCaptchaGenerate smsCaptchaGenerate = new SmsCaptchaGenerate();
        return smsCaptchaGenerate;
    }

    @Bean
    @ConditionalOnMissingBean(DefaultSmsCaptchaSender.class)
    public SmsCaptchaSend defaultSmsCaptchaSender() {
        DefaultSmsCaptchaSender defaultSmsCaptchaSender = new DefaultSmsCaptchaSender();
        return defaultSmsCaptchaSender;
    }
}
