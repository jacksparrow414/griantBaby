package com.graint.baby.code.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author jacksparrow414
 * @Date 2019-09-07
 * @Description: TODO
 */
@Configuration
public class MyThreadPool {

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 获得机器的核心数
        int coreSize = Runtime.getRuntime().availableProcessors();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(8);
        threadPoolTaskExecutor.setKeepAliveSeconds(200);
        threadPoolTaskExecutor.setQueueCapacity(16);
        threadPoolTaskExecutor.setRejectedExecutionHandler( new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setThreadNamePrefix("dhb");
        return threadPoolTaskExecutor;
    }
}
