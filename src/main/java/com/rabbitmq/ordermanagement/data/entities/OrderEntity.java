package com.rabbitmq.ordermanagement.data.entities;

import com.rabbitmq.ordermanagement.data.converter.ListToJsonConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Entity
@Table(name="orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String customerName;
   // @Column(columnDefinition = "BLOB") // Store as JSON string
    //@Convert(converter = ListToJsonConverter.class) // Apply custom converter
   // @Type(org.hibernate.type.TextType.class)
   @Column(name = "items")
    private String items;
    private Double totalPrice;
    private String status;


}
