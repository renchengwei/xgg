package com.xgg.jsf.controller;

import com.xgg.auth.api.request.AuthUserRequest;
import com.xgg.auth.api.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 请描述类的具体实现功能和作用
 *
 * @author renchengwei
 * @date 2019-11-25
 */
@RestController
public class TestController {

    @Autowired
    private IAuthUserService authUserService;

    @RequestMapping("/test")
    public void test(){
        AuthUserRequest request = new AuthUserRequest();
        request.setUserName("rcw");
        request.setPassword("123456");
        request.setMobile("123");
        request.setEmail("33");
        authUserService.saveUser(request);
    }
}
