package com.example.armazem.product;

import java.math.BigDecimal;

public record ProductResponseDTO(Long id, String name, BigDecimal price) {

    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice());
    }
}
