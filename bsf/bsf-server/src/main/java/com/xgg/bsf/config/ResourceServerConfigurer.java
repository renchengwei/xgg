package com.xgg.bsf.config;

/**
 * 资源服务器
 * @author renchengwei
 * @date 2019-10-19
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class ResourceServerConfigurer {
/*    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance){
        return new LoadBalancerInterceptor(loadBalance);
    }*/
    /*@Autowired
    private ResourceServerProperties resourceServerProperties;
    @Autowired
    private OAuth2ClientProperties clientProperties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceServerProperties.getId())
                .tokenServices(remoteTokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**");
        http.authorizeRequests().antMatchers("/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .anonymous().disable()
                .formLogin().disable();
    }

    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        services.setClientId(clientProperties.getClientId());
        services.setClientSecret(clientProperties.getClientSecret());
        services.setRestTemplate(null);
        return services;
    }*/
}
