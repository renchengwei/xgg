package com.xgg.auth.authentication.captcha;

import com.alibaba.fastjson.JSON;
import com.xgg.auth.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
 * 统一的验证码拦截器
 * @author renchengwei
 * @date 2019/8/6
 */
@Component
@Slf4j
public class CaptchaUnionFilter extends OncePerRequestFilter {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private CaptchaProcessorHolder captchaProcessorHolder;

    private Map<String, CaptchaTypeEnum> urlMap = new HashMap<>();

    private Map<String,CaptchaTypeEnum> grantTypeMap = new HashMap<>();

    private static final String GRANT_TYPE_PARAM_NAME = "grant_type";

    private static final String OAUTH_TOKEN_URL = "/oauth/token";

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
        addVaildateUrlToUrlMap(securityProperties.getCaptcha().getSms().getInterceptUrl(), CaptchaTypeEnum.SMS);
        addValidateGrantTypeToGrantMap(securityProperties.getCaptcha().getSms().getGrantType(), CaptchaTypeEnum.SMS);
        //图片验证码
        addVaildateUrlToUrlMap(securityProperties.getCaptcha().getImage().getInterceptUrl(), CaptchaTypeEnum.IMAGE);
        addValidateGrantTypeToGrantMap(securityProperties.getCaptcha().getImage().getGrantType(), CaptchaTypeEnum.IMAGE);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        CaptchaTypeEnum captchaTypeEnum;

        if(isGrantUrl(httpServletRequest))  {
            captchaTypeEnum = getCaptchaTypeWithGrantType(httpServletRequest);
        }else {
            captchaTypeEnum = getCaptchaTypeWithRequestUrl(httpServletRequest);
        }

        if (captchaTypeEnum != null) {
            try {
                log.info("校验请求【" + httpServletRequest.getRequestURI() + "】" + captchaTypeEnum.getDesc() + "验证码");
                captchaProcessorHolder.findCaptchaProcessor(captchaTypeEnum)
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse),captchaTypeEnum);
            } catch (CaptchaException captchaException) {
                log.error("验证码校验异常", captchaException.getMessage());
                onUnsuccessfulAuthentication(httpServletRequest,httpServletResponse,captchaException);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean isGrantUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        if (pathMatcher.match(OAUTH_TOKEN_URL, requestUrl)) {
            return true;
        }
        return false;
    }

    /**
     * 根据登录方式返回验证码类型
     * @param request
     * @return
     */
    private CaptchaTypeEnum getCaptchaTypeWithGrantType(HttpServletRequest request) {
        String grantType = request.getParameter(GRANT_TYPE_PARAM_NAME);
        Set<String> grantSet = grantTypeMap.keySet();
        for (String grant : grantSet) {
            if (pathMatcher.match(grant, grantType)) {
                return grantTypeMap.get(grantType);
            }
        }
        return null;
    }

    /**
     * 根据请求路径返回验证码类型
     *
     * @param request
     * @return
     */
    private CaptchaTypeEnum getCaptchaTypeWithRequestUrl(HttpServletRequest request) {
        //返回除去host（域名或者ip）部分的路径
        if(request.getMethod().equals(HttpMethod.POST.toString()) || request.getMethod().equals(HttpMethod.GET.toString())) {
            String requestUrl = request.getRequestURI();
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

    /**
     * 不同授权模式拦截器赋值
     * @param grantType
     * @param captchaTypeEnum
     */
    private void addValidateGrantTypeToGrantMap(String grantType, CaptchaTypeEnum captchaTypeEnum) {
        if (StringUtils.isNotBlank(grantType)) {
            String[] interceptGrantTypeArray = StringUtils.split(grantType, ",");
            for (String type : interceptGrantTypeArray) {
                grantTypeMap.put(type, captchaTypeEnum);
            }
        }
    }

    protected void onUnsuccessfulAuthentication(HttpServletRequest request,
                                                HttpServletResponse response, CaptchaException failed)
            throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString("验证码错误"));
    }
}
