package com.graint.baby.code.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 自定义返回List.
 */
@Getter
@Setter
public class CustomList<T> {
    
    private List<T> list;
    
}
