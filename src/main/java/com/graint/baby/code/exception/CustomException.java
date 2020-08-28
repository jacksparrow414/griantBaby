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

    public CustomException(final String msg, final int code, final Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
    
}
