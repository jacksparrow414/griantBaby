package com.graint.baby.code.annotation.aspect;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.base.Preconditions;
import com.graint.baby.code.annotation.SysLog;
import com.graint.baby.code.modules.log.service.ISysLogService;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * SysLog切面类.
 */
@Aspect
@Component
public class SysLogAspect {
    
    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Autowired
    private ISysLogService sysLogService;
    
    /**
     * 切点.
     */
    @Pointcut(value = "@annotation(com.graint.baby.code.annotation.SysLog)")
    public void pointCut() {
    }
    
    /**
     * 切面.
     * @param joinPoint 切点
     * @return 执行结果
     */
    @SneakyThrows
    @Around(value = "pointCut()")
    public Object around(final ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog annotation = signature.getMethod().getAnnotation(SysLog.class);
        Preconditions.checkNotNull(annotation, "注解为空");
        Preconditions.checkArgument(StrUtil.isNotBlank(annotation.operationInfo()), "需要操作信息");
        Object[] args = joinPoint.getArgs();
        com.graint.baby.code.modules.log.entity.SysLog sysLog = new com.graint.baby.code.modules.log.entity.SysLog();
        sysLog.setId(IdWorker.getId());
        sysLog.setInfo(annotation.operationInfo());
        sysLog.setParams(JSON.toJSONString(args));
        sysLog.setCreated(LocalDateTime.now());
        threadPoolTaskExecutor.execute(() -> sysLogService.save(sysLog));
        return joinPoint.proceed();
    }
}
