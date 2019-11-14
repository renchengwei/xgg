package com.xgg.auth.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultSecurityUserDeatilsService implements SecurityUserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        return loadUserByUsername("user_1");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String encode = passwordEncoder.encode("123456");
        Collection<GrantedAuthority> authorities = getAuthority();
        User user = new User("user_1",encode,authorities);
        return user;
    }

    private Collection<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        authorities.add(new SimpleGrantedAuthority("CREATE_USER"));
        authorities.add(new SimpleGrantedAuthority("DELETE_USER"));
        return authorities;
    }
}
