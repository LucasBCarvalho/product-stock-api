package com.example.armazem.controller;

import com.example.armazem.product.Product;
import com.example.armazem.product.ProductRepository;
import com.example.armazem.productrawmaterial.*;
import com.example.armazem.rawmaterial.RawMaterial;
import com.example.armazem.rawmaterial.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product-raw-material")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductRawMaterialController {

    @Autowired
    private ProductRawMaterialRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @GetMapping
    public List<ProductRawMaterialResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(ProductRawMaterialResponseDTO::new)
                .toList();
    }

    @GetMapping("/product/{productId}")
    public List<ProductRawMaterialResponseDTO> getByProduct(@PathVariable Long productId) {
        return repository.findByProductId(productId).stream()
                .map(ProductRawMaterialResponseDTO::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductRawMaterialRequestDTO data) {
        Optional<Product> product = productRepository.findById(data.productId());
        Optional<RawMaterial> rawMaterial = rawMaterialRepository.findById(data.rawMaterialId());

        if (product.isEmpty() || rawMaterial.isEmpty()) {
            return ResponseEntity.badRequest().body("Product or RawMaterial not found");
        }

        ProductRawMaterial prm = new ProductRawMaterial(data, product.get(), rawMaterial.get());
        repository.save(prm);
        return ResponseEntity.ok(new ProductRawMaterialResponseDTO(prm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductRawMaterialRequestDTO data) {
        Optional<ProductRawMaterial> optional = repository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Product> product = productRepository.findById(data.productId());
        Optional<RawMaterial> rawMaterial = rawMaterialRepository.findById(data.rawMaterialId());

        if (product.isEmpty() || rawMaterial.isEmpty()) {
            return ResponseEntity.badRequest().body("Product or RawMaterial not found");
        }

        ProductRawMaterial prm = optional.get();
        prm.setProduct(product.get());
        prm.setRawMaterial(rawMaterial.get());
        prm.setQuantityNeeded(data.quantityNeeded());

        repository.save(prm);
        return ResponseEntity.ok(new ProductRawMaterialResponseDTO(prm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<ProductRawMaterial> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}