package com.example.armazem.productrawmaterial;

import java.math.BigDecimal;

public record ProductRawMaterialResponseDTO(
        Long id,
        Long productId,
        String productName,
        Long rawMaterialId,
        String rawMaterialName,
        BigDecimal quantityNeeded
) {
    public ProductRawMaterialResponseDTO(ProductRawMaterial prm) {
        this(
                prm.getId(),
                prm.getProduct().getId(),
                prm.getProduct().getName(),
                prm.getRawMaterial().getId(),
                prm.getRawMaterial().getName(),
                prm.getQuantityNeeded()
        );
    }
}