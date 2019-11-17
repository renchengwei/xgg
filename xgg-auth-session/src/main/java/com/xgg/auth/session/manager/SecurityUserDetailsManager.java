package com.xgg.auth.session.manager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author renchengwei
 * @date 2019-08-11
 * : TODO
 */
public interface SecurityUserDetailsManager extends UserDetailsService {
    UserDetails loadUserByMobile(String mobile);
}
