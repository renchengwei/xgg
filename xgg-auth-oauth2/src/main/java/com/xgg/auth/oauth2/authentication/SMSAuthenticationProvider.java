package com.xgg.auth.oauth2.authentication;

import com.xgg.auth.oauth2.service.SecurityUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author renchengwei
 * @Date 2019/8/6
 * @Description TODO
 */
public class SMSAuthenticationProvider implements AuthenticationProvider {

    @Setter
    @Getter
    private SecurityUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SMSAuthenticationToken smsCaptchaAuthenticationToken= (SMSAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByMobile((String) smsCaptchaAuthenticationToken.getPrincipal());
        if(user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SMSAuthenticationToken authenticationTokenResult = new SMSAuthenticationToken(user,user.getAuthorities());
        //将之前未认证的请求放进认证后的Token中
        authenticationTokenResult.setDetails(smsCaptchaAuthenticationToken.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(SMSAuthenticationToken.class);
    }
}
