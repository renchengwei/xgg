package com.xgg.auth.api.request;

import lombok.Data;

/**
 * TODO 请描述类的具体实现功能和作用
 *
 * @author renchengwei
 * @date 2019-11-24
 */
@Data
public class AuthUserRequest {
    private String password;
    private String userName;
    private String mobile;
    private String email;
}
