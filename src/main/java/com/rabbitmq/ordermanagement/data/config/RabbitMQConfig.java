package com.rabbitmq.ordermanagement.data.config;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RabbitMQConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    public Queue ordersQueue() {
        return new Queue(rabbitMQProperties.getQueueName(), false); // Non-durable queue
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(rabbitMQProperties.getExchangeName());
    }

    @Bean
    public Binding binding(Queue ordersQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(ordersQueue)
                .to(directExchange)
                .with(rabbitMQProperties.getRoutingKey());
    }
}
