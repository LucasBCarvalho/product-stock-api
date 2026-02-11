package com.example.armazem.productrawmaterial;

import java.math.BigDecimal;

public record ProductRawMaterialRequestDTO(Long productId, Long rawMaterialId, BigDecimal quantityNeeded) {}