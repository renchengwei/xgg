package com.xgg.auth.session.config;

import com.xgg.auth.session.DefaultSecurityUserDetailsManager;
import com.xgg.auth.session.SecurityUserDetailsManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
@Configuration
public class SessionBeanConfig {
    @Bean
    @ConditionalOnMissingBean(name = "securityUserDetailsManager")
    public SecurityUserDetailsManager securityUserDetailsManager() {
        return new DefaultSecurityUserDetailsManager();
    }
}
