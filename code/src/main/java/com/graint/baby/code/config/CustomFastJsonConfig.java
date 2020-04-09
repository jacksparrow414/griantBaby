package com.graint.baby.code.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @author duhongbo
 * @date 2020/4/9 14:48
 */
public class CustomFastJsonConfig {

    private static FastJsonConfig fastJsonConfig = null;

    private static FastJsonHttpMessageConverter fastJsonHttpMessageConverter = null;

    /**
     * 自定义fastjson配置
     * @return
     */
    public synchronized  static FastJsonHttpMessageConverter getFastJsonHttpMessageConverter(){
        if(fastJsonHttpMessageConverter == null){
            fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
            List<MediaType> listTypes = Arrays.asList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8);
            fastJsonHttpMessageConverter.setSupportedMediaTypes(listTypes);
            fastJsonHttpMessageConverter.setFastJsonConfig(getOwnConfig());
        }
        return fastJsonHttpMessageConverter;
    }

    private static FastJsonConfig getOwnConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //设置序列化的特征
        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                //    输出key是包含双引号
//                SerializerFeature.QuoteFieldNames,
                //    是否输出为null的字段,若为null 则显示该字段
                SerializerFeature.WriteMapNullValue,
                //    数值字段如果为null，则输出为0
                //SerializerFeature.WriteNullNumberAsZero,
                //     List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //    字符类型字段如果为null,输出为"",而非null
                //SerializerFeature.WriteNullStringAsEmpty,
                //    Boolean字段如果为null,输出为false,而非null
                //SerializerFeature.WriteNullBooleanAsFalse,
                //    Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat,
                //    循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                // 序列化时写入类型信息，默认为false。反序列化时候需用到
                // SerializerFeature.WriteClassName

        };
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, com.alibaba.fastjson.serializer.ToStringSerializer.instance);
        // 防止JS精度丢失,Long全部转为String
        serializeConfig.put(Long.class, com.alibaba.fastjson.serializer.ToStringSerializer.instance);

        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(Charset.defaultCharset());
        return fastJsonConfig;
    }
}
