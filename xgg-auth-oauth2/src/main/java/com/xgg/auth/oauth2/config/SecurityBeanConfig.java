package com.xgg.auth.oauth2.config;

import com.xgg.auth.oauth2.service.DefaultSecurityUserDeatilsService;
import com.xgg.auth.oauth2.service.SecurityUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityBeanConfig {
    @Bean
    @ConditionalOnMissingBean(SecurityUserDetailsService.class)
    public SecurityUserDetailsService securityUserDetailsService() {
        DefaultSecurityUserDeatilsService userDeatilsService = new DefaultSecurityUserDeatilsService();
        return userDeatilsService;
    }
}
