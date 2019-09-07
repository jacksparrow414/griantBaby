package com.graint.baby.code.utils;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义返回状态码和格式
 * @Author jacksparrow414
 * @Date 2019-05-25
 * @Description: TODO
 */
@Component
public class CustomData extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    public CustomData() {
        put("code", 0);
        put("msg", "success");
    }

    public static CustomData error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static CustomData error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static CustomData error(int code, String msg) {
        CustomData customData = new CustomData();
        customData.put("code", code);
        customData.put("msg", msg);
        return customData;
    }

    public static CustomData ok(String msg) {
        CustomData customData = new CustomData();
        customData.put("msg", msg);
        return customData;
    }

    public static CustomData ok(Map<String, Object> map) {
        CustomData customData = new CustomData();
        customData.putAll(map);
        return customData;
    }

    public static CustomData ok() {
        return new CustomData();
    }

    @Override
    public CustomData put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
