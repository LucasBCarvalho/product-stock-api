package com.example.armazem.rawmaterial;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "raw_materials")
@Table(name = "raw_materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private BigDecimal stockQuantity;

    public RawMaterial(RawMaterialRequestDTO data) {
        this.name = data.name();
        this.code = data.code();
        this.stockQuantity = data.stockQuantity();
    }
}