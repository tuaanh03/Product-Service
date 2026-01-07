package com.example.product_service.integration.service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.util.Optional;


public class ProductDataLayerAccessIntegrationTest extends AbstractBaseIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenProductRepository_whenSaveProduct_thenOK() {
        // Test implementation goes here
        // Arrange - Tạo Product với builder (phù hợp với model của bạn)
        Product product = Product.builder()
                .name("Milk")
                .description("1L Milk")
                .price(new BigDecimal("10.00"))
                .isAvailable(true)
                .build();

        // Act - Lưu product vào database (MongoDB Testcontainer)
        Product createdProduct = productRepository.save(product);

        // Assert - Kiểm tra product đã được lưu thành công
        Optional<Product> optionalProduct = productRepository.findById(createdProduct.getId());

        assertThat(optionalProduct).isPresent(); // Kiểm tra có tìm thấy product

        Product retrievedProduct = optionalProduct.get();
        assertThat(retrievedProduct.getId()).isEqualTo(createdProduct.getId());
        assertThat(retrievedProduct.getName()).isEqualTo("Milk");
        assertThat(retrievedProduct.getDescription()).isEqualTo("1L Milk");
        assertThat(retrievedProduct.getPrice()).isEqualTo(new BigDecimal("10.00"));
        assertThat(retrievedProduct.isAvailable()).isTrue();
    }

    public void givenProductRepository_whenFindAllProducts_thenOK() {
        // Test implementation goes here

    }
}
