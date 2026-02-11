package com.example.armazem.productrawmaterial;

import com.example.armazem.product.Product;
import com.example.armazem.rawmaterial.RawMaterial;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "product_raw_materials")
@Table(name = "product_raw_materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductRawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial;

    private BigDecimal quantityNeeded;

    public ProductRawMaterial(ProductRawMaterialRequestDTO data, Product product, RawMaterial rawMaterial) {
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.quantityNeeded = data.quantityNeeded();
    }
}