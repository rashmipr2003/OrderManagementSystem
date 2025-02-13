package com.rabbitmq.ordermanagement.data.consumer;

import com.rabbitmq.ordermanagement.data.dto.OrderMessage;
import com.rabbitmq.ordermanagement.data.entities.OrderEntity;
import com.rabbitmq.ordermanagement.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = "ordersQueue")
    public void receiveOrder(OrderMessage orderMessage) {
        OrderEntity order = new OrderEntity();
        order.setCustomerName(orderMessage.getCustomerName());
        order.setItems(orderMessage.getItems());
        order.setTotalPrice(orderMessage.getTotalPrice());
        order.setStatus("PROCESSING");

        // Save the order in the database
        orderRepository.save(order);
    }
}