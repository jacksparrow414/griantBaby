package com.graint.baby.code.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graint.baby.code.modules.user.dao.SysUserMapper;
import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.graint.baby.code.modules.user.service.SysUserService;
import com.graint.baby.code.sysenum.ShiroEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 服务实现类.
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveUser(final SysUserEntity addUser) {
        String salt = RandomStringUtils.randomAlphabetic(ShiroEnum.HASH_ITERATIONS.getCode());
        addUser.setPassword(new Sha256Hash(addUser.getPassword(), salt, ShiroEnum.HASH_ITERATIONS.getCode()).toHex());
        addUser.setSalt(salt);
        return Optional.of(this.save(addUser)).filter(u -> u.equals(true)).map(result -> addUser.getId()).orElse(null);
    }
}
