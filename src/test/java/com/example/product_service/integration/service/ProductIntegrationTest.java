package com.example.product_service.integration.service;

import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductIntegrationTest extends AbstractBaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Cleanup database trước mỗi test để đảm bảo test độc lập
        productRepository.deleteAll();
    }

    @Test
    public void givenProductService_whenCreateProduct_thenGetProduct() throws Exception {
        // Test implementation goes here
        ProductRequest productRequest = getProductRequest();
        String productRequestJson = objectMapper.writeValueAsString(productRequest);

        // Act 1: Tạo product
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestJson))
                .andExpect(status().isCreated());



        // Act 2: Lấy tất cả products và verify product vừa tạo có trong list
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(status().isOk());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    private ProductRequest getProductRequest()
    {
        return ProductRequest.builder()
                .name("Test Product")
                .description("This is a test product.")
                .price(new java.math.BigDecimal("19.99"))
                .isAvailable(true)
                .build();
    }

}
