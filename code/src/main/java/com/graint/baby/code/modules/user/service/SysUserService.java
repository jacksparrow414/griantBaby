package com.graint.baby.code.modules.user.service;

import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dhb
 * @since 2019-09-07
 */
public interface SysUserService extends IService<SysUserEntity> {

    void saveUser(SysUserEntity addUser);
}
