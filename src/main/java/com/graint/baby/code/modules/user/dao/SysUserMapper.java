package com.graint.baby.code.modules.user.dao;

import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dhb
 * @since 2019-09-07
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    void addUser(@Param("user") SysUserEntity sysUserEntity);
}
