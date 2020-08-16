package com.graint.baby.code.exception;

import com.graint.baby.code.utils.CustomData;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * 自定义异常处理器
 * @Author jacksparrow414
 * @Date 2019-05-25
 * @Description: TODO
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public CustomData handleRRException(CustomException e){
        CustomData customData = new CustomData();
        customData.put("code", e.getCode());
        customData.put("msg", e.getMessage());

        return customData;
    }

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public CustomData handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return CustomData.error(404, "路径不存在，请检查路径是否正确");
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public CustomData handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return CustomData.error("没有权限，请联系管理员授权");
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CustomData handleException(Exception e){
        logger.error(e.getMessage(), e);
        return CustomData.error();
    }
}
