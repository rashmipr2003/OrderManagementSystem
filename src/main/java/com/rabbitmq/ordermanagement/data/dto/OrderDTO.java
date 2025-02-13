package com.rabbitmq.ordermanagement.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDTO {

    @NotBlank
    private String customerName;

    @NotBlank
    @Size(min=1)
    private String items;

    @NotBlank
    private Double totalPrice;

    private String status;
}
