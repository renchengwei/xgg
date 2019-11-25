package com.xgg.auth.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * SpringWebMvc配置文件
 *
 * @author renchengwei
 * @date 2019-11-23
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    /**
     *
     * 使用FastJson替换spring默认的JSON转换器，spring默认使用Jackson
     * @author renchengwei
     * @date 2019-11-23
     * @param
     *
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(Charset.forName("UTF-8"));
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converters.add(0, converter);
    }
}
