package com.graint.baby.code.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContex工具,主要用来bean.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {


    /**
     * 上下文对象实例.
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext.
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name bean的名字
     * @return 具体的类
     */
    public static Object getBean(final String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 类.class
     * @param <T> 具体的类
     * @return 具体的类的对象
     */
    public static <T> T getBean(final Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean.
     *
     * @param name 类名字
     * @param clazz 类.class
     * @param <T> 具体的类
     * @return 具体的类的对象
     */
    public static <T> T getBean(final String name, final Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
