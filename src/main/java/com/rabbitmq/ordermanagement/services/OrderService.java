package com.rabbitmq.ordermanagement.services;

import com.rabbitmq.ordermanagement.data.dto.OrderDTO;
import com.rabbitmq.ordermanagement.data.entities.OrderEntity;
import com.rabbitmq.ordermanagement.data.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    public OrderEntity createOrder(OrderDTO orderDTO){
        OrderEntity order=new OrderEntity();

        order.setCustomerName(orderDTO.getCustomerName());
        order.setItems(orderDTO.getItems());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus("PENDING");

        orderRepository.save(order);
        rabbitTemplate.convertAndSend(queueName,order);

        return order;
    }
}
