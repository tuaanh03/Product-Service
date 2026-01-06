package com.example.product_service.service;

import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .isAvailable(productRequest.isAvailable()) // Hiện tại client có thể set isAvailable khi tạo sản phẩm
                .build();
        // thêm thuộc tính request  buộc phải theo thứ tự trong dto/request

        productRepository.save(product);
        log.info("Product {} created successfully", product.getId());
        // Đoạn này ta có thể remove isAvailable khỏi ProductRequest và set mặc định là true khi tạo sản phẩm
        // Đoạn này ta có thể dùng return thay vì void không ?
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().
                id(product.getId()).
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).
                isAvailable(product.isAvailable()).
                build();
    }
}
