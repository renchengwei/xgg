package com.xgg.auth.authentication.extension.smsauthentication;

import com.xgg.auth.authentication.SecurityConstants;
import com.xgg.auth.authentication.captcha.CaptchaTypeEnum;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 *  @author renchengwei
 *  @date 2019-11-16
 *  : 短信授权登陆授权者，继承AbstractTokenGranter，内部依赖AuthenticationManager，一般是ProviderManager的实现，
 *  最终会通过调用SMSAuthenticationProvider完成用户信息校验
 */
public class SmsCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "sms";
    private final AuthenticationManager authenticationManager;

    protected SmsCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    public SmsCodeTokenGranter(AuthenticationManager authenticationManager,
                               AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String mobile = parameters.get(SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        String smsCode = parameters.get(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);
        parameters.remove(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);
        if (mobile == null) {
            mobile = "";
        }
        mobile = mobile.trim();

        if (smsCode == null) {
            smsCode = "";
        }
        smsCode = smsCode.trim();


        // Protect from downstream leaks of password
        parameters.remove(CaptchaTypeEnum.SMS.getParamNameOnValidate());

        Authentication userAuth = new SmsAuthenticationToken(mobile,smsCode);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        }
        catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + mobile);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
