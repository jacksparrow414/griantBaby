package com.graint.baby.code.modules.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graint.baby.code.modules.role.entity.SysRoleEntity;
import com.graint.baby.code.modules.role.vo.SaveRoleVo;

/**
 * 角色 服务类.
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 角色管理保存.
     * @param saveRoleVo 角色VO
     * @return 成功或失败信息
     */
    String saveRole(SaveRoleVo saveRoleVo);
}
