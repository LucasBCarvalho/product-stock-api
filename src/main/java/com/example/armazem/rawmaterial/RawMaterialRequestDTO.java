package com.example.armazem.rawmaterial;

import java.math.BigDecimal;

public record RawMaterialRequestDTO(
        String name,
        String code,
        BigDecimal stockQuantity
) {}