package com.xgg.auth.config;

import com.xgg.auth.authentication.DefaultSecurityUserDetailsService;
import com.xgg.auth.authentication.SecurityUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renchengwei
 */
@Configuration
public class SecurityBeanConfigurer {
    @Bean
    @ConditionalOnMissingBean(SecurityUserDetailsService.class)
    public SecurityUserDetailsService securityUserDetailsService(){
        DefaultSecurityUserDetailsService detailsService = new DefaultSecurityUserDetailsService();
        return detailsService;
    }

}
