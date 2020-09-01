package com.graint.baby.code.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graint.baby.code.modules.log.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志记录 Mapper 接口.
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
