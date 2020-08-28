package com.graint.baby.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类.
 */
@Configuration
public class ThreadPoolConfig {
    
    /**
     * 具体规则配置.
     *
     * @return org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        taskExecutor.setCorePoolSize(processors);
        taskExecutor.setQueueCapacity(processors << 3);
        taskExecutor.setMaxPoolSize(processors << 4);
        taskExecutor.setKeepAliveSeconds(180);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.setThreadNamePrefix("baby-");
        return taskExecutor;
    }
}
