package com.xgg.auth.session.handler;

import com.alibaba.fastjson.JSON;
import com.xgg.auth.session.properties.SecurityProperties;
import com.xgg.auth.session.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renchengwei
 * @date 2019-08-11
 * : TODO
 */
@Slf4j
public class XggLogoutSuccessHandler implements LogoutSuccessHandler {

    private SecurityProperties securityProperties;

    public XggLogoutSuccessHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String loginOutUrl = securityProperties.getSession().getLoginOut();

        if (StringUtils.isBlank(loginOutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new SimpleResponse("退出成功")));
        } else {
            response.sendRedirect(loginOutUrl);
        }
    }
}
