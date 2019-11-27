package com.xgg.auth.controller;

import com.xgg.bsf.service.IHelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private IHelloService helloService;

    @RequestMapping("/auth/test")
    public String test() {
        return helloService.hello();
    }
}
