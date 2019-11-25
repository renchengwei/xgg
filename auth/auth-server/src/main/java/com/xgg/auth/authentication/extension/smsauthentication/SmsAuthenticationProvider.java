package com.xgg.auth.authentication.extension.smsauthentication;

import com.xgg.auth.authentication.captcha.Captcha;
import com.xgg.auth.authentication.captcha.CaptchaProcessorHolder;
import com.xgg.auth.authentication.captcha.CaptchaTypeEnum;
import com.xgg.auth.authentication.extension.AbstractCommonAuthenticationProvider;
import com.xgg.auth.authentication.SecurityUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * @author renchengwei
 * @date 2019/8/6
 *  短信登陆提供者，内部依赖SecurityUserDetailsService的实现
 * 调用loadUserByMobile方法获取用户信息
 * 从CaptchaProcessorHolder获取用户手机验证码
 * 然后验证smsCode是否匹配，相当于校验密码的操作
 */
public class SmsAuthenticationProvider extends AbstractCommonAuthenticationProvider {

    @Setter
    @Getter
    private SecurityUserDetailsService userDetailsService;

    @Setter
    @Getter
    private CaptchaProcessorHolder captchaProcessorHolder;

    @Override
    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  SmsAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException("手机号或验证码错误");
        }
        String smsCode = null;
        String presentedPassword = authentication.getCredentials().toString();
        Captcha captcha = captchaProcessorHolder.findCaptchaProcessor(CaptchaTypeEnum.SMS).getCaptcha(authentication.getName(),CaptchaTypeEnum.SMS);
        if(captcha != null) {
            smsCode = captcha.getCode();
        }
        if (!StringUtils.equals(presentedPassword, smsCode)) {
            logger.debug("Authentication failed: smsCode does not match stored value");

            throw new BadCredentialsException("手机号或验证码错误");
        }
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    protected final UserDetails retrieveUser(String mobile,
                                             SmsAuthenticationToken authentication)
            throws AuthenticationException {
        try {
            UserDetails loadedUser = this.getUserDetailsService().loadUserByMobile(mobile);


            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        }
        catch (UsernameNotFoundException ex) {
            throw ex;
        }
        catch (InternalAuthenticationServiceException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }
}
