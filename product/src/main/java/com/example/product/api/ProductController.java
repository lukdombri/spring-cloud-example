package com.example.product.api;

import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductLockService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private static final List<ProductDTO> PRODUCT_DTOS = Arrays.asList(
            ProductDTO.builder().id("1").name("Product_1").details("Details about product first").build(),
            ProductDTO.builder().id("2").name("Product_2").details("Details about product second").build(),
            ProductDTO.builder().id("3").name("Product_3").details("Details about product third").build()
    );

    private final ProductLockService productLockService;


    @SneakyThrows
    @PutMapping("/product/db")
    @ResponseStatus(HttpStatus.OK)
    public Product productFromDb(@RequestBody Product product) {
        return productLockService.updateProductWithLock(product);
    }


    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO product(@RequestParam String id) {
        log.info("Wywołanie usługi /product dla productId = " + id);
        return PRODUCT_DTOS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElseThrow(NotFoundException::new);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> products() {
        log.info("Wywołanie usługi /products");
        return PRODUCT_DTOS;
    }

    @GetMapping("/products-filtered")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> products(@RequestParam List<String> ids) {
        log.info("Wywołanie usługi /products z parametrem");
        return PRODUCT_DTOS.stream()
                .filter(p -> ids.stream().anyMatch(id -> p.getId().equals(id)))
                .collect(Collectors.toList());
    }

}
