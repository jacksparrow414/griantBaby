package com.graint.baby.code;
//
//import java.nio.charset.Charset;
//import java.util.HashMap;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @Author jacksparrow414
// * @Date 2019-09-07
// * @Description: TODO
// * rabbitMQ测试类
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RabbitMqTests {
//
//    @Test
//    public void contexLoad() {
//    }
//
//    @Autowired
//    RabbitAdmin rabbitAdmin;
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    @Test
//    public void testAdmin() {
//        rabbitAdmin.declareExchange(new DirectExchange("test.direct", false, false));
//        rabbitAdmin.declareExchange(new TopicExchange("test.topic", false, false));
//        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout", false, false));
//
//        rabbitAdmin.declareQueue(new Queue("test.direct.queue", false));
//        rabbitAdmin.declareQueue(new Queue("test.topic.queue", false));
//        rabbitAdmin.declareQueue(new Queue("test.fanout.queue", false));
//        rabbitAdmin.declareBinding(new Binding("test.direct.queue", Binding.DestinationType.QUEUE, "test.direct", "direct", new HashMap<>()));
//
//        // BindingBuilder链式编程，bind-绑定的队列，to-绑定的交换机,with-路由的key
//        rabbitAdmin.declareBinding(
//                BindingBuilder
//                        .bind(new Queue("test.topic.queue", false))
//                        .to(new TopicExchange("test.topic", false, false))
//                        .with("user.#"));
//
//        rabbitAdmin.declareBinding(
//                BindingBuilder
//                .bind(new Queue("test.fanout.queue",false))
//                .to(new FanoutExchange("test.fanout",false,false))
//        );
//
//        rabbitAdmin.purgeQueue("test.topic.queue",false);
//    }
//
//    @Test
//    public void testSendMessage(){
//        //1.创建消息
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.getHeaders().put("desc","信息描述...");
//        messageProperties.getHeaders().put("type","自定义类型...");
//        // 消息体
//        Message message = new Message("hello world".getBytes(Charset.defaultCharset()),messageProperties);
//        // 转换并发送
//        rabbitTemplate.convertAndSend("topic001", "spring.amqp", message, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                System.out.println("------添加的额外配置------");
//                message.getMessageProperties().getHeaders().put("desc","额外的配置");
//                message.getMessageProperties().getHeaders().put("attr","添加的额外属性");
//                return message;
//            }
//        });
//
//    }
//
//    /**
//     * 简便写法
//     */
//    @Test
//    public void testSendMessage2(){
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setContentType("text/plain");
//
//        Message message = new Message("mq消息".getBytes(Charset.defaultCharset()),messageProperties);
//
//        rabbitTemplate.send("topic001","spring.abc",message);
//
//        rabbitTemplate.convertAndSend("topic001","spring.amqp","这是我饿消息");
//        rabbitTemplate.convertAndSend("topic002","rabbit.abc","this is a message");
//    }
//}
