package com.xgg.auth.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 * 用户查询接口，扩展了spring默认的UserDetailsService接口，添加按照手机号码查询用户的方法
 * @author renchengwei
 * @date 2019-11-18
 */
public interface SecurityUserDetailsService extends UserDetailsService {

    /**
     *
     * 按照手机号查询用户信息
     * @author renchengwei
     * @date 2019-11-18
     * @param mobile 手机号码
     * @return UserDetails 用户详情
     *
     */
    UserDetails loadUserByMobile(String mobile);

}
