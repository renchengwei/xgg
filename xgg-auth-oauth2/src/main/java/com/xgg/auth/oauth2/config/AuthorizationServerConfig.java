package com.xgg.auth.oauth2.config;

import com.xgg.auth.oauth2.authentication.CustomTokenServices;
import com.xgg.auth.oauth2.authentication.SmsAuthenticationProvider;
import com.xgg.auth.oauth2.authentication.SmsCodeTokenGranter;
import com.xgg.auth.oauth2.captcha.CaptchaProcessorHolder;
import com.xgg.auth.oauth2.captcha.CaptchaUnionFilter;
import com.xgg.auth.oauth2.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author renchengwei
 * @date 2019-10-05
 * : TODO
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private SecurityUserDetailsService userDetailsService;
   /* @Autowired
    RedisConnectionFactory redisConnectionFactory;*/
    /*@Resource
    private DataSource dataSource;*/
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CaptchaUnionFilter CaptchaUnionFilter;
    @Autowired
    private CaptchaProcessorHolder captchaProcessorHolder;


    private static final String DEMO_RESOURCE_ID = "order";
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        String finalSecret = new BCryptPasswordEncoder().encode("");
        /*clients.jdbc(dataSource);*/
        clients.inMemory().withClient("demo")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password","refresh_token","sms","authorization_code","implicit")
                .scopes("all")
                .redirectUris("http://localhost:9001/callback");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       /* endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .reuseRefreshTokens(true);*/
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .tokenGranter(tokenGranter(endpoints));
        endpoints.tokenServices(customTokenServices());
    }

    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        SmsCodeTokenGranter smsCodeTokenGranter = new SmsCodeTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        tokenGranters.add(smsCodeTokenGranter);
        tokenGranters.add(endpoints.getTokenGranter());
        if (authenticationManager instanceof ProviderManager) {
            SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
            smsAuthenticationProvider.setUserDetailsService(userDetailsService);
            smsAuthenticationProvider.setCaptchaProcessorHolder(captchaProcessorHolder);
            ((ProviderManager) authenticationManager).getProviders().add(smsAuthenticationProvider);
        }
        endpoints.getClientDetailsService();
        return new CompositeTokenGranter(tokenGranters);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer  oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder)
                .addTokenEndpointAuthenticationFilter(CaptchaUnionFilter);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Primary
    public CustomTokenServices customTokenServices() {
        CustomTokenServices tokenServices = new CustomTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        addUserDetailsService(tokenServices,userDetailsService);
        return tokenServices;
    }

    private void addUserDetailsService(CustomTokenServices tokenServices, UserDetailsService userDetailsService) {
        if (userDetailsService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(
                    userDetailsService));
            tokenServices
                    .setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider> asList(provider)));
        }
    }
}
