package com.xgg.auth.session.config;

import com.xgg.auth.session.handler.XggLogoutSuccessHandler;
import com.xgg.auth.session.manager.DefaultSecurityUserDetailsManager;
import com.xgg.auth.session.manager.SecurityUserDetailsManager;
import com.xgg.auth.session.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
@Configuration
public class SessionBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;


    @Bean
    @ConditionalOnMissingBean(name = "securityUserDetailsManager")
    public SecurityUserDetailsManager securityUserDetailsManager() {
        return new DefaultSecurityUserDetailsManager();
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler tigerLogoutSuccessHandler(){
        return new XggLogoutSuccessHandler(securityProperties);
    }
}
