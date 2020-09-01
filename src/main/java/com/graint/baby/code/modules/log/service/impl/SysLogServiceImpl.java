package com.graint.baby.code.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graint.baby.code.modules.log.dao.SysLogMapper;
import com.graint.baby.code.modules.log.entity.SysLog;
import com.graint.baby.code.modules.log.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * 日志记录Service实现类.
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
