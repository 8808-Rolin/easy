package com.tokentest.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // 1.定义fanout类型的交换器
    @Bean
    public Exchange fanout_exchange(){
        return ExchangeBuilder.fanoutExchange("fanout_exchange").build();
    }

    // 2.定义两个不同的消息队列
    @Bean
    public Queue fanout_queue_email(){
        return new Queue("fanout_queue_email");
    }
    @Bean
    public Queue fanout_queue_mes(){
        return new Queue("fanout_queue_mes");
    }

    // 3.Putting the queue with different names bind to fanout_exchange
    @Bean
    public Binding bindingEmail(){
        return BindingBuilder.bind(fanout_queue_email()).to(fanout_exchange()).with("").noargs();
    }
    @Bean
    public Binding bindingMes(){
        return BindingBuilder.bind(fanout_queue_mes()).to(fanout_exchange()).with("").noargs();
    }

}
