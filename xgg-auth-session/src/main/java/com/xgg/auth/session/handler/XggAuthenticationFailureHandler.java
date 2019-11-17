package com.xgg.auth.session.handler;

import com.alibaba.fastjson.JSON;
import com.xgg.auth.session.enums.LoginTypeEnum;
import com.xgg.auth.session.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renchengwei
 * @date 2019-08-03
 * : 自定义登陆失败处理器
 */
@Component("xggAuthenticationFailureHandler")
@Slf4j
public class XggAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败:" + exception.getMessage());
        if (LoginTypeEnum.JSON.equals(securityProperties.getSession().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(exception));
        } else {
            // 如果用户配置为跳转，则跳到Spring Boot默认的错误页面
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
