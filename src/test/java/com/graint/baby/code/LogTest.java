package com.graint.baby.code;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author duhongbo
 * @date 2020/3/31 9:28
 */
@SpringBootTest
public class LogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog(){
        this.testDebugLog();
        this.testInfoLog();
        this.testWarnLog();
        this.testErrorLog();
    }

    private void testDebugLog(){
        LOGGER.debug("这是debug日志");
    }
    private void testInfoLog(){
        LOGGER.info("这是info日志");
    }
    private void testWarnLog(){
        LOGGER.warn("这是warn日志");
    }
    private void testErrorLog(){
        LOGGER.error("这是error日志");
    }
}
