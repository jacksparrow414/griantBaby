package com.graint.baby.code.exception;

import com.graint.baby.code.utils.CustomData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 自定义异常处理器.
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    
    /**
     * 处理自定义异常.
     *
     * @param e CustomException
     * @return com.graint.baby.code.utils.CustomData
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public CustomData handleCustomException(final CustomException e) {
        CustomData customData = new CustomData();
        customData.put("code", e.getCode());
        customData.put("msg", e.getMessage());
        
        return customData;
    }
    
    /**
     * 路径找不到异常.
     *
     * @param e NoHandlerFoundException 路径找不到异常
     * @return com.graint.baby.code.utils.CustomData
     */
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public CustomData handlerNoFoundException(final NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return CustomData.error(404, "路径不存在，请检查路径是否正确");
    }
    
    /**
     * 权限校验异常.
     *
     * @param e AuthorizationException异常
     * @return com.graint.baby.code.utils.CustomData
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public CustomData handleAuthorizationException(final AuthorizationException e) {
        log.error(e.getMessage(), e);
        return CustomData.error("没有权限，请联系管理员授权");
    }
    
    /**
     * 最大异常.
     *
     * @param e Exception
     * @return com.graint.baby.code.utils.CustomData
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CustomData handleException(final Exception e) {
        log.error(e.getMessage(), e);
        return CustomData.error();
    }
}
