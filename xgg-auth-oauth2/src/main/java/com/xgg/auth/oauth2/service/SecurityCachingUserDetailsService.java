package com.xgg.auth.oauth2.service;

import lombok.Data;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 带有缓存功能的用户操作服务，默认缓存为NullUserCache
 *
 * @author renchengwei
 * @date 2019-11-19
 */
@Data
public class SecurityCachingUserDetailsService implements SecurityUserDetailsService {
    private UserCache userCache = new NullUserCache();
    @Resource(name = "securityUserDetailsService")
    private final SecurityUserDetailsService delegate;

    /**
     * @param delegate
     */
    public SecurityCachingUserDetailsService(SecurityUserDetailsService delegate) {
        this.delegate = delegate;
    }

    public UserCache getUserCache() {
        return userCache;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = userCache.getUserFromCache(username);

        if (user == null) {
            user = delegate.loadUserByUsername(username);
        }

        Assert.notNull(user, () -> "SecurityUserDetailsService " + delegate
                + " returned null for username " + username + ". "
                + "This is an interface contract violation");

        userCache.putUserInCache(user);

        return user;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        UserDetails user = userCache.getUserFromCache(mobile);

        if (user == null) {
            user = delegate.loadUserByMobile(mobile);
        }

        Assert.notNull(user, () -> "SecurityUserDetailsService " + delegate
                + " returned null for mobile " + mobile + ". "
                + "This is an interface contract violation");

        userCache.putUserInCache(user);

        return user;
    }
}
