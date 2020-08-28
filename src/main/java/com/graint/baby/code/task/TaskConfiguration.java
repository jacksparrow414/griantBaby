package com.graint.baby.code.task;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;

/**
 * 任务配置类.
 */
//@Configuration
public class TaskConfiguration implements SchedulingConfigurer {
    
    /*
     * @Autowired 是按照类型装配，@Resource是按照名称装配，Autowired可以再加一个注解@Qualifier注解再指定名字可以达到和Resource一样的效果.
     */
    
    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    
    @Override
    public void configureTasks(final ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor);
    }
}
