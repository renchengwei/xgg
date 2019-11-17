package com.xgg.auth.session.config;

import com.xgg.auth.session.manager.SecurityUserDetailsManager;
import com.xgg.auth.session.authentication.SmsAuthenticationFilter;
import com.xgg.auth.session.authentication.SmsAuthenticationProvider;
import com.xgg.auth.session.handler.XggAuthenticationFailureHandler;
import com.xgg.auth.session.handler.XggAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author renchengwei
 * @date 2019/8/6
 *  TODO
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private XggAuthenticationFailureHandler xggAuthenticationFailureHandler;
    @Resource
    private XggAuthenticationSuccessHandler xggAuthenticationSuccessHandler;
    @Resource
    private SecurityUserDetailsManager userDetailsManager;
    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsAuthenticationFilter filter = new SmsAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(xggAuthenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(xggAuthenticationSuccessHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsManager(userDetailsManager);

        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
