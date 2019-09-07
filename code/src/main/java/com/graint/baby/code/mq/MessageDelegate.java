package com.graint.baby.code.mq;

/**
 * @Author jacksparrow414
 * @Date 2019-09-07
 * @Description: TODO
 */
public class MessageDelegate {

    public void consumeMessage(String messageBody) {
        System.out.println("字符串方法, 消息内容:" + messageBody);
    }
}
