package com.xgg.auth.session.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.xgg.auth.session.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 验证码生成配置类
 */
@Configuration
public class KaptchaGenerateConfig {

    @Autowired
    private SecurityProperties securityProperties;

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
