package com.graint.baby.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RedisTemplate配置类.
 */
@Configuration
public class RestTemplateConfig {
    
    /**
     * 配置具体规则.
     *
     * @return org.springframework.web.client.RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(60);
        httpRequestFactory.setReadTimeout(120);
        return new RestTemplate(httpRequestFactory);
    }
}
