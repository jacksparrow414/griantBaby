package com.graint.baby.code.sysenum;

/**
 * @author jacksparrow414
 * @date 2020-03-14
 * @description: Shiro枚举类
 */
public enum ShiroEnum {
    /**
     * shiro加盐的次数
     */
    HASH_ITERATIONS(20,"HashIterations"),
    /**
     * shiro选择的加密算法
     */
    HASH_ALGORITHMNAME(256,"SHA-256");
    private Integer code;
    private String name;

    ShiroEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }}
