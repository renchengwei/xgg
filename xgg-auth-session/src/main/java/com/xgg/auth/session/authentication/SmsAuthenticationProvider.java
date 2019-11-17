package com.xgg.auth.session.authentication;

import com.xgg.auth.session.manager.SecurityUserDetailsManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author renchengwei
 * @date 2019/8/6
 *  TODO
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    @Setter
    @Getter
    private SecurityUserDetailsManager userDetailsManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsCaptchaAuthenticationToken= (SmsAuthenticationToken) authentication;
        UserDetails user = userDetailsManager.loadUserByMobile((String) smsCaptchaAuthenticationToken.getPrincipal());
        if(user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsAuthenticationToken authenticationTokenResult = new SmsAuthenticationToken(user,user.getAuthorities());
        //将之前未认证的请求放进认证后的Token中
        authenticationTokenResult.setDetails(smsCaptchaAuthenticationToken.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(SmsAuthenticationToken.class);
    }
}
