package com.example.orders.api;

import com.example.orders.client.ProductClient;
import com.example.orders.dto.OrderDTO;
import com.example.orders.dto.OrderDetailsDTO;
import com.example.orders.dto.ProductDTO;
import com.example.orders.dto.ProductHeaderDTO;
import com.example.orders.service.OrderService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> orders() {
        return orderService.getOrders();
    }

    @GetMapping("/order-details")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetailsDTO ordersDetails(@RequestParam String orderId) {
        return orderService.getOrderDetails(orderId);
    }
}
