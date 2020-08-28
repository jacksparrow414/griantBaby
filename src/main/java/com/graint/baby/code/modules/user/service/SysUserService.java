package com.graint.baby.code.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graint.baby.code.modules.user.entity.SysUserEntity;

/**
 * 服务类.
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 添加用户.
     * @param addUser 用户实体
     * @return 用户ID
     */
    Long saveUser(SysUserEntity addUser);
}
