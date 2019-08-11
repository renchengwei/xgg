package com.xgg.auth.demo.service.impl;

import com.xgg.auth.demo.pojo.po.AuthorityPO;
import com.xgg.auth.demo.dao.AuthorityMapper;
import com.xgg.auth.demo.service.IAuthorityService;
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
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AuthorityPO> implements IAuthorityService {

}
