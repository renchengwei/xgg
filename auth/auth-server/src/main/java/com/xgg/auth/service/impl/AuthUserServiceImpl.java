package com.xgg.auth.service.impl;

import com.xgg.auth.api.request.AuthUserRequest;
import com.xgg.auth.api.service.IAuthUserService;
import com.xgg.auth.dao.AuthUserMapper;
import com.xgg.auth.pojo.entity.AuthUserDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 *
 * TODO 请描述类的具体实现功能和作用
 * @author renchengwei
 * @since 2019-11-24
 */
@Service
public class AuthUserServiceImpl implements IAuthUserService {

    @Resource
    AuthUserMapper authUserMapper;

    @Override
    public void saveUser(AuthUserRequest userRequest) {
        AuthUserDO userDO = new AuthUserDO();
        BeanUtils.copyProperties(userRequest,userDO);
        userDO.setDeleted(false);
        userDO.setGmtCreate(LocalDateTime.now());
        userDO.setGmtModified(LocalDateTime.now());
        userDO.setLocked(false);
        authUserMapper.insert(userDO);
    }
}
