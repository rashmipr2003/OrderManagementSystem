package com.rabbitmq.ordermanagement.data.repository;

import com.rabbitmq.ordermanagement.data.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
