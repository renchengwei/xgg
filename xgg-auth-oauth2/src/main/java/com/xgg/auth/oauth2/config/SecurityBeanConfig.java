package com.xgg.auth.oauth2.config;

import com.xgg.auth.oauth2.service.DefaultSecurityUserDetailsService;
import com.xgg.auth.oauth2.service.SecurityUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renchengwei
 */
@Configuration
public class SecurityBeanConfig {
    @Bean
    @ConditionalOnMissingBean(name = "securityUserDetailsService")
    public SecurityUserDetailsService securityUserDetailsService() {
        DefaultSecurityUserDetailsService userDeatilsService = new DefaultSecurityUserDetailsService();
        return userDeatilsService;
    }

}
