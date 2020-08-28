package com.graint.baby.code.utils;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义返回状态码和格式.
 */
@Component
public class CustomData<T> extends HashMap<String, Object> {
    
    private T data;
    
    public CustomData() {
        put("code", 0);
        put("msg", "success");
    }
    
    @Override
    public CustomData put(final String key, final Object value) {
        super.put(key, value);
        return this;
    }
    
    /**
     * 通用错误返回.
     *
     * @return com.graint.baby.code.utils.CustomData
     */
    public static CustomData error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }
    
    /**
     * 状态码、信息定义返回信息.
     *
     * @param code 错误码
     * @param msg 错误信息
     * @return com.graint.baby.code.utils.CustomData
     */
    public static CustomData error(final int code, final String msg) {
        CustomData customData = new CustomData();
        customData.put("code", code);
        customData.put("msg", msg);
        return customData;
    }
    
    /**
     * 错误信息返回.
     *
     * @param msg 错误信息
     * @return com.graint.baby.code.utils.CustomData
     */
    public static CustomData error(final String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
    
    /**
     * 成功信息返回.
     *
     * @param msg 成功信息
     * @return com.graint.baby.code.utils.CustomData
     */
    public static CustomData ok(final String msg) {
        CustomData customData = new CustomData();
        customData.put("msg", msg);
        return customData;
    }
    
    /**
     * 成功信息返回.
     *
     * @param map 信息集合
     * @return com.graint.baby.code.utils.CustomData
     */
    public static CustomData ok(final Map<String, Object> map) {
        CustomData customData = new CustomData();
        customData.putAll(map);
        return customData;
    }
    
    /**
     * 成功信息返回,直接返回默认信息.
     *
     * @return com.graint.baby.code.utils.CustomData
     */
    public static CustomData ok() {
        return new CustomData();
    }
    
    /**
     * 获取Data.
     *
     * @return T
     */
    public T getData() {
        return data;
    }
    
    /**
     * 设置Data.
     *
     * @param data data数据
     */
    public void setData(final T data) {
        this.data = data;
    }
}
