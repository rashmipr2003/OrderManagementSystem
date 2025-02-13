package com.rabbitmq.ordermanagement.data.config;

//import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration
@Data
public class RabbitMQConfig {


    @Bean
    public Queue orderQueue(@Value("${rabbitmq.queue.name}") String queueName) {
        return new Queue(queueName, true);
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("ordersExchange");
    }

    @Bean
    public Binding binding(Queue ordersQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(ordersQueue)
                .to(directExchange)
                .with("orderRoutingKey");
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connection1Factory = new CachingConnectionFactory("localhost");  // Replace with your RabbitMQ host
        connection1Factory.setUsername("guest");  // Default username
        connection1Factory.setPassword("guest");  // Default password
        return connection1Factory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter jsonMessageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter);
        return factory;
    }
//    @Bean
//    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
//                                                             MessageListener messageListener) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
//        container.setMessageListener(messageListener);
//        return container;
//    }
}
