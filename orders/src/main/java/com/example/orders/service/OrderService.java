package com.example.orders.service;

import com.example.orders.client.ProductClient;
import com.example.orders.dto.OrderDTO;
import com.example.orders.dto.OrderDetailsDTO;
import com.example.orders.dto.ProductDTO;
import com.example.orders.dto.ProductHeaderDTO;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final List<OrderDTO> orders = Arrays.asList(
            OrderDTO.builder().id("1").date(LocalDate.of(2023, 1, 1)).user("adam.nowak@gmail.com")
                    .productHeaderList(Arrays.asList(ProductHeaderDTO.builder().id("1").amount(1).build())).build(),
            OrderDTO.builder().id("2").date(LocalDate.of(2023, 2, 1)).user("adam.nowak@gmail.com")
                    .productHeaderList(Arrays.asList(
                            ProductHeaderDTO.builder().id("1").amount(1).build(),
                            ProductHeaderDTO.builder().id("3").amount(2).build())).build(),
            OrderDTO.builder().id("3").date(LocalDate.of(2022, 12, 20)).user("adam.nowak@gmail.com")
                    .productHeaderList(Arrays.asList(
                            ProductHeaderDTO.builder().id("2").amount(4).build(),
                            ProductHeaderDTO.builder().id("3").amount(5).build())
                    ).build());

    private final ProductClient productClient;

    public OrderDetailsDTO getOrderDetails(String orderId) {
        OrderDTO order = orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().orElseThrow(NotFoundException::new);
        //Wywołanie usługi /products-filtered z mikroserwisu product z użyciem Feign
        List<ProductDTO> products = productClient.getProducts(order.getProductHeaderList().stream().map(ProductHeaderDTO::getId).toList());

        return OrderDetailsDTO.builder()
                .id(order.getId())
                .date(order.getDate())
                .productHeaderList(order.getProductHeaderList())
                .products(products)
                .user(order.getUser())
                .build();
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }
}
