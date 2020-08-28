package com.graint.baby.code.modules.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graint.baby.code.modules.user.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper 接口.
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 添加用户.
     *
     * @param sysUserEntity 用户实体类
     */
    void addUser(@Param("user") SysUserEntity sysUserEntity);
}
