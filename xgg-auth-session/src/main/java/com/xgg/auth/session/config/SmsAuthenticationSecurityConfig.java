package com.xgg.auth.session.config;

import com.xgg.auth.session.SecurityUserDetailsManager;
import com.xgg.auth.session.authentication.SmsAuthenticationFilter;
import com.xgg.auth.session.authentication.SmsAuthenticationProvider;
import com.xgg.auth.session.handler.XggAuthenticationFailureHandler;
import com.xgg.auth.session.handler.XggAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author renchengwei
 * @Date 2019/8/6
 * @Description TODO
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private XggAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private XggAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SecurityUserDetailsManager userDetailsManager;
    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsAuthenticationFilter filter = new SmsAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsManager(userDetailsManager);

        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
