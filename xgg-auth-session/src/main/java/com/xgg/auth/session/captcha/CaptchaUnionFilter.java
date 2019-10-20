package com.xgg.auth.session.captcha;

import com.xgg.auth.session.authentication.SecurityConstants;
import com.xgg.auth.session.handler.XggAuthenticationFailureHandler;
import com.xgg.auth.session.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author renchengwei
 * @Date 2019/8/6
 * @Description TODO
 */
@Component
@Slf4j
public class CaptchaUnionFilter extends OncePerRequestFilter implements InitializingBean {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private CaptchaProcessorHolder captchaProcessorHolder;
    @Resource
    private XggAuthenticationFailureHandler xggAuthenticationFailureHandler;

    /**
     * 存放所有需要校验验证码的url
     * key: 验证码类型
     * value: 验证路径
     */
    private Map<String, CaptchaTypeEnum> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * bean初始化后调用
     *
     * @throws ServletException
     */

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //短信验证码
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, CaptchaTypeEnum.SMS);
        addVaildateUrlToUrlMap(securityProperties.getCaptcha().getSms().getInterceptUrl(), CaptchaTypeEnum.SMS);
        //图片验证码
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, CaptchaTypeEnum.IMAGE);
        addVaildateUrlToUrlMap(securityProperties.getCaptcha().getImage().getInterceptImageUrl(), CaptchaTypeEnum.IMAGE);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        CaptchaTypeEnum captchaTypeEnum = getCaptchaTypeWithRequestUrl(httpServletRequest);
        if (captchaTypeEnum != null) {
            try {
                log.info("校验请求【" + httpServletRequest.getRequestURI() + "】" + captchaTypeEnum.getDesc() + "验证码");
                captchaProcessorHolder.findCaptchaProcessor(captchaTypeEnum)
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse),captchaTypeEnum);
            } catch (CaptchaException captchaException) {
                log.info("验证码校验异常", captchaException.getMessage());
                xggAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, captchaException);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    /**
     * 根据请求路径返回验证码类型
     *
     * @param request
     * @return
     */
    private CaptchaTypeEnum getCaptchaTypeWithRequestUrl(HttpServletRequest request) {
        //返回除去host（域名或者ip）部分的路径

        String requestUrl = request.getRequestURI();

        if (!HttpMethod.GET.matches(request.getMethod())) {
            Set<String> urlSet = urlMap.keySet();
            for (String url : urlSet) {
                if (pathMatcher.match(url, requestUrl)) {
                    return urlMap.get(url);
                }
            }
        }
        return null;
    }

    /**
     * 不同类型拦截路径赋值
     *
     * @param interceptUrl
     * @param captchaTypeEnum
     */
    private void addVaildateUrlToUrlMap(String interceptUrl, CaptchaTypeEnum captchaTypeEnum) {
        if (StringUtils.isNotBlank(interceptUrl)) {
            String[] interceptUrlArray = StringUtils.split(interceptUrl, ",");
            for (String url : interceptUrlArray) {
                urlMap.put(url, captchaTypeEnum);
            }
        }
    }
}
