package com.graint.baby.code.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author jacksparrow414
 * @Date 2019-09-01
 * @Description: 在spring中如何要实现定时任务：
 * 1.最简单的办法，在要实行定时任务的类上加@Scheduled注解，指定定时周期或者CRON表达式即可，并且加入@EnableScheduling注解使之生效
 * 2.在第一步中，所有的定时任务都默认由一个单线程执行的，如果要让所有定时任务多线程执行，互不干扰，则可以配置线程池
 * 3.配置线程池有两种方法，一种是直接新建一个线程池，注入即可，想下面这样。同时在定时任务方法上加入@Async注解，在类上加入@EnableAsync注解
 * 4.另一种办法是实现SchedulingConfigurer接口，重写configureTasks方法，在这里指定线程池,{@link TaskConfiguration}
 * 5.第二种方法不仅可以实现多线程执行定时任务，还可以动态的添加、删除、重置定时任务，
 * 具体可以参考{@see <a href =https://blog.csdn.net/qq_27721169/article/details/88029436>Spring定时任务</a>}
 */
@Component
//@EnableScheduling
@EnableAsync
public class MyTask {

//    @Autowired 是按照类型装配，@Resource是按照名称装配，Autowired可以再加一个注解@Qualifier注解再指定名字可以达到和Resource一样的效果
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Scheduled(fixedRate = 5000)
    @Async
    public void taskOne() throws InterruptedException {
        System.out.println("我是第一个任务");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);

    }
    @Scheduled(fixedRate = 5000)
    @Async
    public void taskTwo() throws InterruptedException {
        System.out.println("我是第二个任务");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);

    }
    @Scheduled(fixedRate = 5000)
    @Async
    public void taskThree(){
        System.out.println("我是第三个任务");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(Thread.currentThread().getName());

    }

}
