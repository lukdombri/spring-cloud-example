package com.example.orders.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductHeaderDTO {
    private String id;
    private Integer amount;
}
