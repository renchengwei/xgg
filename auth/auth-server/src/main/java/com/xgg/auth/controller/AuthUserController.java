package com.xgg.auth.controller;


import com.xgg.auth.api.request.AuthUserRequest;
import com.xgg.auth.api.service.IAuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO 请描述类的具体实现功能和作用
 * @author renchengwei
 * @date 2019-11-24
 *
 */
@RestController
@RequestMapping("/auth_user")
public class AuthUserController {

    @Resource
    private IAuthUserService authUserService;
    @RequestMapping("/save_user")
    public ResponseEntity<String> saveUser(AuthUserRequest request) {
        authUserService.saveUser(request);
        return ResponseEntity.ok("用户保存成功");
    }
}

