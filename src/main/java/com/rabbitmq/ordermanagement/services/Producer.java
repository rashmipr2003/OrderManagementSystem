package com.rabbitmq.ordermanagement.services;

import com.rabbitmq.ordermanagement.data.dto.OrderDTO;
import com.rabbitmq.ordermanagement.data.dto.OrderMessage;
import com.rabbitmq.ordermanagement.data.entities.OrderEntity;
import com.rabbitmq.ordermanagement.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Value("ordersQueue")
   // @Value("${rabbitmq.queue.name}")
    private String queueName;

    public OrderEntity createOrder(OrderDTO orderDTO){
        OrderEntity order=new OrderEntity();

        order.setCustomerName(orderDTO.getCustomerName());
        order.setItems(orderDTO.getItems());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus("PENDING");

        orderRepository.save(order);
        // Convert OrderEntity to OrderMessage (DTO)
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setCustomerName(order.getCustomerName());
        orderMessage.setItems(order.getItems());
        orderMessage.setTotalPrice(order.getTotalPrice());
        orderMessage.setStatus(order.getStatus());

        rabbitTemplate.convertAndSend(queueName, orderMessage);

        System.out.println("Message sent to RabbitMQ: " + orderMessage);
        return order;
    }
}
