package com.example.orders.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@SuperBuilder
public class OrderDTO {
    private String id;
    private LocalDate date;
    private List<ProductHeaderDTO> productHeaderList;
    private String user;
}
