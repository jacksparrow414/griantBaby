package com.graint.baby.code.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常.
 */
@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    
    private final String msg;
    
    private final int code;
    
}
