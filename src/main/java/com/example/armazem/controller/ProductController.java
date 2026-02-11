package com.example.armazem.controller;

import com.example.armazem.product.Product;
import com.example.armazem.product.ProductRepository;
import com.example.armazem.product.ProductRequestDTO;
import com.example.armazem.product.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> productList = repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productList;
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data) {
        Product productData = new Product(data);
        repository.save(productData);
        return;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO data) {
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setCode(data.code());
            product.setPrice(data.price());

            repository.save(product);
            return ResponseEntity.ok(product);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}