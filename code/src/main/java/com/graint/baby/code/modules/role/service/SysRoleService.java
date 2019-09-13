package com.graint.baby.code.modules.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graint.baby.code.modules.role.entity.SysRoleEntity;
import com.graint.baby.code.modules.role.vo.SaveRoleVo;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author dhb
 * @since 2019-09-13
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 角色管理保存
     * @param saveRoleVo
     * @return
     */
    String saveRole(SaveRoleVo saveRoleVo);
}
