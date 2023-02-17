package com.example.orders.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class OrderDetailsDTO extends OrderDTO{
    private List<ProductDTO> products;
}
