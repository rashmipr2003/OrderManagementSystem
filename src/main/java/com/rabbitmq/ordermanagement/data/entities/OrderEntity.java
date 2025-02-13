package com.rabbitmq.ordermanagement.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String customerName;
    private String items;
    private Double totalPrice;
    private String status;
}
