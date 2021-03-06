package com.xgg.auth.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.xgg.auth.authentication.captcha.CaptchaGenerate;
import com.xgg.auth.authentication.captcha.CaptchaRepository;
import com.xgg.auth.authentication.captcha.InMemoryCaptchaRepository;
import com.xgg.auth.authentication.captcha.image.ImageCaptchaGenerate;
import com.xgg.auth.authentication.captcha.sms.DefaultSmsCaptchaSender;
import com.xgg.auth.authentication.captcha.sms.SmsCaptchaGenerate;
import com.xgg.auth.authentication.captcha.sms.SmsCaptchaSend;
import com.xgg.auth.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 验证码Bean生成配置类
 */
@Configuration
public class CaptchaBeanConfigurer {

    @Autowired
    private SecurityProperties securityProperties;

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
    @ConditionalOnMissingBean(CaptchaRepository.class)
    public CaptchaRepository inMemoryCaptchaRepository() {
        InMemoryCaptchaRepository inMemoryCaptchaRepository = new InMemoryCaptchaRepository();
        return inMemoryCaptchaRepository;
    }

    @Bean
    @ConditionalOnMissingBean(DefaultSmsCaptchaSender.class)
    public SmsCaptchaSend defaultSmsCaptchaSender() {
        DefaultSmsCaptchaSender defaultSmsCaptchaSender = new DefaultSmsCaptchaSender();
        return defaultSmsCaptchaSender;
    }

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        //常量配置Constants和直接字符串配置都可以
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");

        properties.setProperty("kaptcha.textproducer.char.length", String.valueOf(securityProperties.getCaptcha().getImage().getSize()));

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
