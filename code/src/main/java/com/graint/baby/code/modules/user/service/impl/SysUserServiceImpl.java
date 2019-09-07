package com.graint.baby.code.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graint.baby.code.modules.user.dao.SysUserMapper;
import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.graint.baby.code.modules.user.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dhb
 * @since 2019-09-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Override
    public void saveUser(SysUserEntity addUser) {
        String salt = RandomStringUtils.randomAlphabetic(20);
        addUser.setPassword(new Sha256Hash(addUser.getPassword(), salt, 20).toHex());
        addUser.setSalt(salt);
        this.save(addUser);
    }
}
