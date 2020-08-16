package com.graint.baby.code.task;

import javax.annotation.Resource;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @Author jacksparrow414
 * @Date 2019-09-01
 * @Description: TODO
 */
//@Configuration
public class TaskConfiguration implements SchedulingConfigurer {

    /*
     *
     * @author duhongbo
     * @param null
     * @return
     * @Autowired 是按照类型装配，@Resource是按照名称装配，Autowired可以再加一个注解@Qualifier注解再指定名字可以达到和Resource一样的效果
     *
     */

    @Resource(name = "threadPoolTaskExecutor")
    ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor);
    }
}