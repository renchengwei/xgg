package com.xgg.auth.oauth2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityUserDetailsService extends UserDetailsService {

    UserDetails loadUserByMobile(String mobile);

}
