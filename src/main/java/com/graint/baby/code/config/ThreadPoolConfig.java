package com.graint.baby.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jacksparrow414
 * @date 2020/3/30 16:49
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        taskExecutor.setCorePoolSize(processors);
        taskExecutor.setQueueCapacity(processors<<3);
        taskExecutor.setMaxPoolSize(processors<<4);
        taskExecutor.setKeepAliveSeconds(180);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.setThreadNamePrefix("baby-");
        return taskExecutor;
    }
}
