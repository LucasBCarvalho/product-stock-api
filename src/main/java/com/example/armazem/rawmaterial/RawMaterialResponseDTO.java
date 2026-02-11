package com.example.armazem.rawmaterial;

import java.math.BigDecimal;

public record RawMaterialResponseDTO(
        Long id,
        String name,
        String code,
        BigDecimal stockQuantity
) {
    public RawMaterialResponseDTO(RawMaterial rawMaterial) {
        this(
                rawMaterial.getId(),
                rawMaterial.getName(),
                rawMaterial.getCode(),
                rawMaterial.getStockQuantity()
        );
    }
}