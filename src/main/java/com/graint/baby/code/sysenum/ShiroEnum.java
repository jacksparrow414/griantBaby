package com.graint.baby.code.sysenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Shiro枚举类.
 */
@Getter
@AllArgsConstructor
public enum ShiroEnum {
    
    /**
     * shiro加盐的次数.
     */
    HASH_ITERATIONS(20, "HashIterations"),
    /**
     * shiro选择的加密算法.
     */
    HASH_ALGORITHMNAME(256, "SHA-256");
    
    private final Integer code;
    
    private final String name;
    
}
