package com.rabbitmq.ordermanagement.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage implements Serializable {
    private String customerName;
    private String items;
    private Double totalPrice;
    private String status;



    @JsonProperty("customer_name")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("items")
    public String getItems() {
        return items;
    }

    @JsonProperty("total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

}
