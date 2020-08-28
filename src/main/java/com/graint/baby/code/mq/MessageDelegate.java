package com.graint.baby.code.mq;

/**
 * 接收消息.
 */
public class MessageDelegate {
    
    /**
     * 简单接收消息.
     *
     * @param messageBody 消息体
     */
    public void consumeMessage(final String messageBody) {
        System.out.println("字符串方法, 消息内容:" + messageBody);
    }
}
