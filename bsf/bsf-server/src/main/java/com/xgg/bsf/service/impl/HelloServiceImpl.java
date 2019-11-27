package com.xgg.bsf.service.impl;

import com.xgg.auth.service.IAuthUserService;
import com.xgg.bsf.service.IHelloService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloServiceImpl implements IHelloService {

    @Resource
    private IAuthUserService authUserService;
    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String callAuth() {
        return authUserService.helloUser();
    }
}
