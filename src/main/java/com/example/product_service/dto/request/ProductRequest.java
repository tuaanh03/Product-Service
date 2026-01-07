package com.example.product_service.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        boolean isAvailable)
{}
