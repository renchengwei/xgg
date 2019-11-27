package com.xgg.bsf.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("bsf-server")
public interface IHelloService {
    @RequestMapping("/bsf/hello")
    String hello();
    @RequestMapping("/bsf/call_auth")
    String callAuth();
}
