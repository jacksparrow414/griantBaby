package com.graint.baby.code.task;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author jacksparrow414
 * @Date 2019-09-01
 * @Description: TODO
 */
//@Configuration
public class TaskConfiguration implements SchedulingConfigurer {

    private ScheduledTaskRegistrar taskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        taskRegistrar.setScheduler(executorService);
       // this.taskRegistrar = taskRegistrar;
    }
}