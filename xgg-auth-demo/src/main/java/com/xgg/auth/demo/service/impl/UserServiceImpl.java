package com.xgg.auth.demo.service.impl;

import com.xgg.auth.demo.pojo.po.UserPO;
import com.xgg.auth.demo.dao.UserMapper;
import com.xgg.auth.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renchengwei
 * @since 2019-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

}
