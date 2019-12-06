package com.xgg.auth.controller;

import com.xgg.bsf.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private IHelloService helloService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/auth/test")
    public String test() {
        return helloService.hello();
    }
    @RequestMapping("/auth/test1")
    public String test1() {
       String str =  restTemplate.getForObject("http://bsf-server/test/test1",String.class);
       return str;
    }
}
