package com.example.product.api;

import com.example.product.dto.Product;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private static final List<Product> products = Arrays.asList(
            Product.builder().id("1").name("Product_1").details("Details about product first").build(),
            Product.builder().id("2").name("Product_2").details("Details about product second").build(),
            Product.builder().id("3").name("Product_3").details("Details about product third").build()
    );

    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public Product product(@RequestParam String id){
        log.info("Wywołanie usługi /product dla productId = " + id);
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElseThrow(NotFoundException::new);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> products(){
        log.info("Wywołanie usługi /products");
        return products;
    }

    @GetMapping("/products-filtered")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> products(@RequestParam List<String> ids){
        log.info("Wywołanie usługi /products z parametrem");
        return products.stream()
                .filter(p -> ids.stream().anyMatch(id -> p.getId().equals(id)))
                .collect(Collectors.toList());
    }
}
