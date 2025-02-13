package com.rabbitmq.ordermanagement.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @NotBlank
    private String customerName;

    @NotBlank
    @Size(min=1)
    private String items;

    @NotNull(message = "Total Price cannot be null")
    @Positive(message = "Total Price should be positive")
    private Double totalPrice;

    private String status;


}
