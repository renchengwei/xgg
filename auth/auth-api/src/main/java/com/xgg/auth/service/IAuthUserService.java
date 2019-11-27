package com.xgg.auth.service;

import com.xgg.auth.request.AuthUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * TODO 请描述接口的具体实现功能和作用
 * @author renchengwei
 * @since 2019-11-24
 */
@FeignClient("auth-server")
public interface IAuthUserService {

    /**
     *
     * 保存授权会员信息
     * @author renchengwei
     * @date 2019-11-24
     * @param userRequest 会员保存请求信息
     *
     */
    @RequestMapping("/auth/user/save_user")
    void saveUser(AuthUserRequest userRequest);
    @RequestMapping("/auth/user/hello_user")
    String helloUser();
}
