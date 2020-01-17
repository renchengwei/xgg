package com.xgg.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * TODO 请描述类的具体实现功能和作用
 *
 * @author renchengwei
 * @date 2019/12/23
 */
@Configuration
@EnableWebFluxSecurity
public class WebfluxSecurityConfig {

//    @Autowired
//    private SecurityContextRepository securityContextRepository;
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//       http.formLogin().disable()
//               .csrf().disable()
//               .anonymous().disable()
//               .httpBasic().disable()
//               .securityContextRepository(securityContextRepository)
//               .authorizeExchange().pathMatchers("/oauth/**").permitAll()
//               .anyExchange().authenticated()
//               .and()
//               .build();
//    }
}


