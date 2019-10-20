package com.xgg.auth.session.manager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
public interface SecurityUserDetailsManager extends UserDetailsService {
    UserDetails loadUserByMobile(String mobile);
}
