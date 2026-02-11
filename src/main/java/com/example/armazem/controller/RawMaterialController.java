package com.example.armazem.controller;

import com.example.armazem.rawmaterial.RawMaterial;
import com.example.armazem.rawmaterial.RawMaterialRepository;
import com.example.armazem.rawmaterial.RawMaterialRequestDTO;
import com.example.armazem.rawmaterial.RawMaterialResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("raw-material")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RawMaterialController {

    @Autowired
    private RawMaterialRepository repository;

    @GetMapping
    public List<RawMaterialResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(RawMaterialResponseDTO::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<RawMaterial> save(@RequestBody RawMaterialRequestDTO data) {
        RawMaterial rawMaterial = new RawMaterial(data);
        repository.save(rawMaterial);
        return ResponseEntity.ok(rawMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> update(@PathVariable Long id, @RequestBody RawMaterialRequestDTO data) {
        Optional<RawMaterial> optional = repository.findById(id);

        if (optional.isPresent()) {
            RawMaterial rawMaterial = optional.get();
            rawMaterial.setName(data.name());
            rawMaterial.setCode(data.code());
            rawMaterial.setStockQuantity(data.stockQuantity());

            repository.save(rawMaterial);
            return ResponseEntity.ok(rawMaterial);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<RawMaterial> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}