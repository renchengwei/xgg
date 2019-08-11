package com.xgg.auth.session.config;

import com.xgg.auth.session.SecurityConstants;
import com.xgg.auth.session.SecurityUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *  @author: renchengwei
 *  @Date: 2019-08-03
 *  @Description: 浏览器安全配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class SessionSecurityConfig extends AbstractChannelSecurityConfig {


    @Resource
    private DataSource dataSource;
    @Resource
    private SecurityUserDetailsManager userDetailsManager;
    @Resource
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Resource
    private CaptchaSecurityConfig captchaSecurityConfig;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


    /**
     * 记住我持久化数据源
     * JdbcTokenRepositoryImpl  CREATE_TABLE_SQL 建表语句可以先在数据库中执行
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //第一次会执行CREATE_TABLE_SQL建表语句 后续会报错 可以关掉
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(smsAuthenticationSecurityConfig);
        http.apply(captchaSecurityConfig);
        applyPasswordAuthenticationConfig(http);
        authorizeRequestsConfig(http);
        rememberMeConfig(http);
        sessionConfig(http);
        http.csrf().disable();
    }

    private void sessionConfig(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .maximumSessions(securityProperties.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getSession().isMaxSessionsPreventsLogin())
                .expiredUrl(securityProperties.getSession().getInvalidSessionUrl());
    }

    private void authorizeRequestsConfig(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(securityProperties.getSession().getLoginPage(),
                        securityProperties.getSession().getLoginProcessingUrl(),
                        SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    private void rememberMeConfig(HttpSecurity http) throws Exception {
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getSession().getRemberMeSeconds())
                .userDetailsService(userDetailsManager);
    }

}
