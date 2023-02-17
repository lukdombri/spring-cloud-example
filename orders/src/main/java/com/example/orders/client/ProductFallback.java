package com.example.orders.client;

import com.example.orders.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ProductFallback implements ProductClient{
    @Override
    public List<ProductDTO> getProducts(List<String> ids) {
        log.info("/products-filtered FALLBACK METHOD");
        return Collections.emptyList();
    }
}
