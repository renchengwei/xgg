package com.xgg.auth.session.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  @author: renchengwei
 *  @Date: 2019-08-11
 *  @Description:
 *
 */
@Slf4j
public class DefaultSecurityUserDetailsManager implements SecurityUserDetailsManager {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String encode = passwordEncoder.encode("123456");
        Collection<GrantedAuthority> authorities = getAuthority();
        User user = new User("admin",encode,authorities);
        return user;
    }

    private Collection<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("create_user"));
        authorities.add(new SimpleGrantedAuthority("delete_user"));
        return authorities;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        return loadUserByUsername(null);
    }
}
