package com.graint.baby.code.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ配置类.
 *
 * <p>rabbitAdmin和rabbitTemplate任选一种即可</p>
 *
 * <p>rabbitTemplate:
 * 我们在与SpringAMQP整合的时候进行发送消息的关键词
 * </p>
 *
 * <p>
 * 该类提供了丰富的发送消息方法，
 * 包括可靠性投递消息方法、
 * 回调监听消息接口ConfirmCallback、
 * 返回值确认接口ReturnCallback等等。
 * 同样我们需要进行注入到Spring容器中，然后直接使用,在与SPring整合时需要实例化，但是在与SpringBoot整合时，在配置文件里添加配置即可</p>
 *
 * <p>1. 设置交换机类型
 * 2. 将队列绑定到交换机
 * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
 * HeadersExchange ：通过添加属性key-value匹配
 * DirectExchange:按照routingkey分发到指定队列
 * TopicExchange:多关键字匹配</p>
 */
@Configuration
public class RabbitMqConfig {
    
    /**
     * 配置连接工厂.
     *
     * @return org.springframework.amqp.rabbit.connection.ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("127.0.0.1:5672");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }
    
    /**
     * 配置RabbitAdmin.
     *
     * @param connectionFactory 连接工厂
     * @return org.springframework.amqp.rabbit.core.RabbitAdmin
     */
    @Bean
    public RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
    
    /**
     * 配置RabbitTemplate.
     *
     * @param connectionFactory 连接工厂
     * @return org.springframework.amqp.rabbit.core.RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
    
    /**
     * 设置队列001.
     *
     * @return org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue queue001() {
        return new Queue("queue001", true);
    }
    
    /**
     * 设置关键字匹配交换机001.
     *
     * @return org.springframework.amqp.core.TopicExchange
     */
    @Bean
    public TopicExchange exchange001() {
        return new TopicExchange("topic001", true, false);
    }
    
    /**
     * 将队列001绑定到交换机001上.
     *
     * @return org.springframework.amqp.core.Binding
     */
    @Bean
    public Binding binding001() {
        return BindingBuilder.bind(queue001()).to(exchange001()).with("spring.*");
    }
    
    /**
     * 设置队列002.
     *
     * @return org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue queue002() {
        return new Queue("queue002", true);
    }
    
    /**
     * 设置关键字匹配交换机002.
     *
     * @return org.springframework.amqp.core.TopicExchange
     */
    @Bean
    public TopicExchange exchange002() {
        return new TopicExchange("topic002", true, false);
    }
    
    /**
     * 将队列002绑定到交换机002上.
     *
     * @return org.springframework.amqp.core.Binding
     */
    @Bean
    public Binding binding002() {
        return BindingBuilder.bind(queue002()).to(exchange002()).with("rabbit.*");
    }
    
    /**
     * 设置队列003.
     *
     * @return org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue queue003() {
        return new Queue("queue003", true);
    }
    
    /**
     * 将队列003绑定到交换机001上.
     *
     * @return org.springframework.amqp.core.Binding
     */
    @Bean
    public Binding binding003() {
        return BindingBuilder.bind(queue003()).to(exchange001()).with("mq.*");
    }
    
    /**
     * 消息监听.
     *
     * @param connectionFactory 连接工厂
     * @return org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(final ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setDefaultListenerMethod("consumeMessage");
        adapter.setMessageConverter(new TextMessageConverter());
        container.setMessageListener(adapter);
        return container;
    }
}
