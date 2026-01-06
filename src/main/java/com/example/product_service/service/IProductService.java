package com.example.product_service.service;

import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
