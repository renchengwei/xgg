package com.xgg.auth.demo.controller;

import com.xgg.auth.demo.pojo.vo.ResponseVO;
import com.xgg.auth.session.enums.LoginTypeEnum;
import com.xgg.auth.session.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
@RestController
@Slf4j
public class SessionRequireController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseVO<Object> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(LoginTypeEnum.REDIRECT.equals(securityProperties.getSession().getLoginType())) {
            redirectStrategy.sendRedirect(request, response, securityProperties.getSession().getRequireUrl());
        }
        return new ResponseVO<>(0,"访问的服务需要身份认证，请引导用户到登录页面");
    }

}
