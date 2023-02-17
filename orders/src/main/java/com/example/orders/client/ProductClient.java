package com.example.orders.client;

import com.example.orders.dto.OrderDetailsDTO;
import com.example.orders.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="product", fallback = ProductFallback.class)
public interface ProductClient {

    @GetMapping("/products-filtered")
    List<ProductDTO> getProducts(@RequestParam("ids") List<String> ids);
}
