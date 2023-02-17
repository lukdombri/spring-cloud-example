package com.example.orders.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private String id;
    private String name;
    private String details;
}
