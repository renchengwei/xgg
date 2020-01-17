package com.xgg.bsf.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 请描述类的具体实现功能和作用
 *
 * @author renchengwei
 * @date 2019/12/1
 */
@RestController
public class TestController {

    @RequestMapping("/test/test1")
    @PreAuthorize("hasRole('USER_DELETE')")
    public String test1() {
        return "test1";
    }
}
