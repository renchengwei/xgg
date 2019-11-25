package com.xgg.auth.api.service;

import com.xgg.auth.api.request.AuthUserRequest;

/**
 *
 * TODO 请描述接口的具体实现功能和作用
 * @author renchengwei
 * @since 2019-11-24
 */
public interface IAuthUserService {

    /**
     *
     * 保存授权会员信息
     * @author renchengwei
     * @date 2019-11-24
     * @param userRequest 会员保存请求信息
     *
     */
    void saveUser(AuthUserRequest userRequest);
}
