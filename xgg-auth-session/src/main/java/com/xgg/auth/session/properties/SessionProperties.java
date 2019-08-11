package com.xgg.auth.session.properties;

import com.xgg.auth.session.SecurityConstants;
import com.xgg.auth.session.enums.LoginTypeEnum;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
public class SessionProperties {

    /**
     * 默认登录地址
     */
    private String loginPage = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;
    private String loginProcessingUrl = SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM;
    private String successForwardUrl = SecurityConstants.DEFAULT_LOGIN_SUCCESS_FORWARD_URL;
    private String failureForwardUrl = SecurityConstants.DEFAULT_LOGIN_FAIL_FORWARD_URL;
    private LoginTypeEnum loginType = LoginTypeEnum.REDIRECT;
    /**
     * 默认登录页面
     */
    private String requireUrl = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private int remberMeSeconds = 3600;


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public String getFailureForwardUrl() {
        return failureForwardUrl;
    }

    public void setFailureForwardUrl(String failureForwardUrl) {
        this.failureForwardUrl = failureForwardUrl;
    }

    public String getRequireUrl() {
        return requireUrl;
    }

    public void setRequireUrl(String requireUrl) {
        this.requireUrl = requireUrl;
    }

    public int getRemberMeSeconds() {
        return remberMeSeconds;
    }

    public void setRemberMeSeconds(int remberMeSeconds) {
        this.remberMeSeconds = remberMeSeconds;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }
}
