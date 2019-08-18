package com.xgg.auth.session.config;

import com.xgg.auth.session.handler.XggAuthenticationFailureHandler;
import com.xgg.auth.session.handler.XggAuthenticationSuccessHandler;
import com.xgg.auth.session.properties.SecurityProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private XggAuthenticationFailureHandler xggAuthenticationFailureHandler;
    @Resource
    private XggAuthenticationSuccessHandler xggAuthenticationSuccessHandler;
    @Resource
    protected SecurityProperties securityProperties;

    /**
     * 密码登录配置
     * @param http
     * @throws Exception
     */
    protected void 	applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(securityProperties.getSession().getLoginPage())
                .loginProcessingUrl(securityProperties.getSession().getLoginProcessingUrl())
                .defaultSuccessUrl(securityProperties.getSession().getSuccessForwardUrl())
                .failureForwardUrl(securityProperties.getSession().getFailureForwardUrl())
                .failureHandler(xggAuthenticationFailureHandler)
                .successHandler(xggAuthenticationSuccessHandler);
    }
}
