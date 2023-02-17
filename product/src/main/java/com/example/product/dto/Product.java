package com.example.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String id;
    private String name;
    private String details;
}
