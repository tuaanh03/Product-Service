package com.example.product_service.unit.service;

import com.example.product_service.dto.request.ProductRequest;
import com.example.product_service.dto.response.ProductResponse;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService - createProduct() Tests")
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    private Product mockProduct;
    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    public void setUp() {
        mockProduct = Product.builder()
                .id("prod-123")
                .name("Test Product")
                .description("This is a test product.")
                .price(new BigDecimal("19.99"))
                .isAvailable(true)
                .build();

        productRequest = ProductRequest.builder()
                .name("Test Product")
                .description("This is a test product.")
                .price(new BigDecimal("19.99"))
                .isAvailable(true)
                .build();
    }

    // ==================== getAllProducts() Tests ====================
    @Test
    @DisplayName("getAllProducts() - Placeholder Test")
    void getAllProducts_placeholderTest() {
        // Arrange - Giả lập hành vi của productRepository
        when(productRepository.findAll()).thenReturn(List.of(mockProduct));

        // Act - Gọi hàm thật cần test
        List<ProductResponse> resultGetAllProducts = productService.getAllProducts();

        // Assert - Kiểm tra kết quả trả về
        // Then phase - Kiểm tra kết quả đầu ra
        assertEquals(1, resultGetAllProducts.size());
        assertThat(resultGetAllProducts.get(0).id()).isNotNull();
        assertEquals("Test Product", resultGetAllProducts.get(0).name());
        assertEquals("This is a test product.", resultGetAllProducts.get(0).description());
        assertEquals(new BigDecimal("19.99"), resultGetAllProducts.get(0).price());
        assertTrue(resultGetAllProducts.get(0).isAvailable());

        verify(productRepository, times(1)).findAll();

    }


//    // ==================== createProduct() Tests ====================
//
//    @Test
//    @DisplayName("Given valid ProductRequest - When createProduct - Then repository.save() is called once")
//    void givenValidProductRequest_whenCreateProduct_thenSaveIsCalled() {
//        // Arrange
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//        // tức là test khi thằng nào dùng productRepository.save(any(Product.class)) thì nó sẽ trả về mockProduct với dữ liệu giả
//        // Act
//        productService.createProduct(productRequest);
//
//        // Assert
//        verify(productRepository, times(1)).save(any(Product.class));
//    }
//
//    @Test
//    @DisplayName("Given ProductRequest - When createProduct - Then Product name is mapped correctly")
//    void givenProductRequest_whenCreateProduct_thenNameIsMappedCorrectly() {
//        // Arrange
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//        // Act
//        productService.createProduct(productRequest);
//
//        // Assert
//        verify(productRepository).save(productCaptor.capture());
//        Product savedProduct = productCaptor.getValue();
//        assertThat(savedProduct.getName()).isEqualTo("Test Product");
//    }
//
//    @Test
//    @DisplayName("Given ProductRequest - When createProduct - Then Product description is mapped correctly")
//    void givenProductRequest_whenCreateProduct_thenDescriptionIsMappedCorrectly() {
//        // Arrange
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//        // Act
//        productService.createProduct(productRequest);
//
//        // Assert
//        verify(productRepository).save(productCaptor.capture());
//        Product savedProduct = productCaptor.getValue();
//        assertThat(savedProduct.getDescription()).isEqualTo("This is a test product.");
//    }
//
//    @Test
//    @DisplayName("Given ProductRequest - When createProduct - Then Product price is mapped correctly")
//    void givenProductRequest_whenCreateProduct_thenPriceIsMappedCorrectly() {
//        // Arrange
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//        // Act
//        productService.createProduct(productRequest);
//
//        // Assert
//        verify(productRepository).save(productCaptor.capture());
//        Product savedProduct = productCaptor.getValue();
//        assertThat(savedProduct.getPrice()).isEqualTo(new BigDecimal("19.99"));
//    }
//
//    @Test
//    @DisplayName("Given ProductRequest with isAvailable=true - When createProduct - Then Product isAvailable is true")
//    void givenProductRequestWithIsAvailableTrue_whenCreateProduct_thenIsAvailableIsTrue() {
//        // Arrange
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//        // Act
//        productService.createProduct(productRequest);
//
//        // Assert
//        verify(productRepository).save(productCaptor.capture());
//        Product savedProduct = productCaptor.getValue();
//        assertThat(savedProduct.isAvailable()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Given ProductRequest with isAvailable=false - When createProduct - Then Product isAvailable is false")
//    void givenProductRequestWithIsAvailableFalse_whenCreateProduct_thenIsAvailableIsFalse() {
//        // Arrange
//        ProductRequest unavailableRequest = ProductRequest.builder()
//                .name("Unavailable Product")
//                .description("This product is not available")
//                .price(new BigDecimal("29.99"))
//                .isAvailable(false)
//                .build();
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//        // Act
//        productService.createProduct(unavailableRequest);
//
//        // Assert
//        verify(productRepository).save(productCaptor.capture());
//        Product savedProduct = productCaptor.getValue();
//        assertThat(savedProduct.isAvailable()).isFalse();
//    }
//
//    @Test
//    @DisplayName("Given ProductRequest - When createProduct - Then all fields are mapped correctly")
//    void givenProductRequest_whenCreateProduct_thenAllFieldsAreMappedCorrectly() {
//        // Arrange
//        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//        // Act
//        productService.createProduct(productRequest);
//
//        // Assert
//        verify(productRepository).save(productCaptor.capture());
//        Product savedProduct = productCaptor.getValue();
//
//        assertThat(savedProduct.getName()).isEqualTo(productRequest.name());
//        assertThat(savedProduct.getDescription()).isEqualTo(productRequest.description());
//        assertThat(savedProduct.getPrice()).isEqualTo(productRequest.price());
//        assertThat(savedProduct.isAvailable()).isEqualTo(productRequest.isAvailable());
//    }
}
