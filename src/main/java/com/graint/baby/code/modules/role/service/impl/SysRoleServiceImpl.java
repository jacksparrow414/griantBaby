package com.graint.baby.code.modules.role.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graint.baby.code.common.SysConstants;
import com.graint.baby.code.modules.role.dao.SysRoleMapper;
import com.graint.baby.code.modules.role.entity.SysRoleEntity;
import com.graint.baby.code.modules.role.service.SysRoleService;
import com.graint.baby.code.modules.role.vo.SaveRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色 服务实现类.
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {
    
    @Override
    public String saveRole(final SaveRoleVo saveRoleVo) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        if (SysConstants.COMMON_ZERO.equals(saveRoleVo.getOperationType()) && ObjectUtils.isNull(saveRoleVo.getRoleId())) {
            BeanUtils.copyProperties(saveRoleVo, sysRoleEntity);
        }
        if (SysConstants.COMMON_ONE.equals(saveRoleVo.getOperationType()) && ObjectUtils.isNotNull(saveRoleVo.getRoleId())) {
            sysRoleEntity = this.getById(saveRoleVo.getRoleId());
            if (ObjectUtils.isNull(sysRoleEntity)) {
                return SysConstants.NO_EXIST_DATA;
            }
            BeanUtils.copyProperties(saveRoleVo, sysRoleEntity);
        }
        boolean result = this.saveOrUpdate(sysRoleEntity);
        if (result) {
            return "";
        }
        return SysConstants.SAVE_FAILURE;
    }
}
