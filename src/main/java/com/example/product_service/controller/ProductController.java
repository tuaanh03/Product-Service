package com.example.product_service.controller;


import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public record ProductController(IProductService productService)
{
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        log.info("Creating product...", productRequest);
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products...");
        return productService.getAllProducts();
    }
}

