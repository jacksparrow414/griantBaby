package com.graint.baby.code.config;

import com.graint.baby.code.CodeApplication;
import com.graint.baby.code.common.SysConstants;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author jacksparrow414
 * @Date 2019-09-14
 * @Description: TODO
 *
 * 通过实现 WebMvcConfigurer 接口来实现对跨域的支持
 *
 * 如果自定义了拦截器,使用此方法配置之后再使用自定义拦截器时跨域相关配置就会失效,
 * 可以直接在启动类上配置CorsFilter{@link CodeApplication#corsFilter()}
 * 相关文章可以参考{@see <a href=https://www.codercto.com/a/55519.html></a>}
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*").allowedMethods("*")
                .allowCredentials(true).allowedOrigins("*")
                .maxAge(3600);
    }

    /**
     * 加入自定义的fastjson配置,并把它放到converters中的第一个
     * @author jacksparrow414
     * @param converters 转换器
     * @return void
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(SysConstants.COMMON_ZERO,CustomFastJsonConfig.getFastJsonHttpMessageConverter());
    }
}
