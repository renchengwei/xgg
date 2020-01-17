//package com.xgg.bsf.config;
//
///**
// * 资源服务器
// * @author renchengwei
// * @date 2019-10-19
// */
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.web.client.DefaultResponseErrorHandler;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
//
//    @Autowired
//    private ResourceServerProperties resourceServerProperties;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
//        remoteTokenServices.setClientId(resourceServerProperties.getClientId());
//        remoteTokenServices.setClientSecret(resourceServerProperties.getClientSecret());
//        remoteTokenServices.setRestTemplate(restTemplate());
//        resources.resourceId(resourceServerProperties.getId())
//                .tokenServices(remoteTokenServices);
//    }
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
//            @Override
//            // Ignore 400
//            public void handleError(ClientHttpResponse response) throws IOException {
//                if (response.getRawStatusCode() != 400) {
//                    super.handleError(response);
//                }
//            }
//        });
//        return restTemplate;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
////        http.requestMatchers().antMatchers("/api/**");
//        http.authorizeRequests().anyRequest().authenticated()
//            .and().anonymous().disable()
//                .formLogin().disable();
//    }
//
//}
