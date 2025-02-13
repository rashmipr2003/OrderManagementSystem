package com.rabbitmq.ordermanagement.controllers;

import com.rabbitmq.ordermanagement.data.dto.OrderDTO;
import com.rabbitmq.ordermanagement.data.entities.OrderEntity;
import com.rabbitmq.ordermanagement.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ApiOperation(value = "Create a new order", notes = "Accepts order data, saves it, and sends it to RabbitMQ.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order created successfully"),
            @ApiResponse(code = 400, message = "Invalid order data")
    })
    public ResponseEntity<OrderEntity> createOrder(@Valid @RequestBody OrderDTO orderDTO){
        OrderEntity createdOrder =orderService.createOrder(orderDTO);

        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
