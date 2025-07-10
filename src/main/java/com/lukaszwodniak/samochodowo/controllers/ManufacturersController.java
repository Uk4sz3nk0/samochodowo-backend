package com.lukaszwodniak.samochodowo.controllers;

import com.lukaszwodniak.samochodowo.handlers.ManufacturersHandler;
import com.lukaszwodniak.samochodowo.models.dto.ManufacturerDto;
import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * ManufacturersController
 * <br>
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacturers")
public class ManufacturersController {

    private final ManufacturersHandler manufacturersHandler;

    @GetMapping
    public ResponseEntity<Page<ManufacturerDto>> getManufacturers(final Pageable pageable, @RequestParam @Nullable String phrase) {
        log.debug("Request \"getManufacturers\" has called");
        var manufacturers = manufacturersHandler.handleGetManufacturers(pageable, phrase);
        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getManufacturer(@PathVariable UUID id) {
        log.debug("Request \"getManufacturer\" has called");
        var manufacturer = manufacturersHandler.handleGetManufacturerById(id);
        return ResponseEntity.ok(manufacturer);
    }

    @GetMapping("/{id}/models")
    public ResponseEntity<Page<ModelDto>> getManufacturerModels(@PathVariable UUID id, Pageable pageable) {
        log.debug("Request \"getManufacturerModels\" has called");
        var models = manufacturersHandler.handleGetModels(id, pageable);
        return ResponseEntity.ok(models);
    }

    @PostMapping
    public ResponseEntity<ManufacturerDto> addManufacturer(@RequestBody @Valid ManufacturerDto manufacturer) {
        log.debug("Request \"addManufacturer\" has called");
        var addedManufacturer = manufacturersHandler.handleAddManufacturer(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addedManufacturer);
    }

    @PatchMapping
    public ResponseEntity<ManufacturerDto> updateManufacturer(@RequestBody @Valid ManufacturerDto manufacturer) {
        log.debug("Request \"updateManufacturer\" has called");
        var updatedManufacturer = manufacturersHandler.handleUpdateManufacturer(manufacturer);
        return ResponseEntity.ok(updatedManufacturer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable UUID id) {
        log.debug("Request \"deleteManufacturer\" has called");
        manufacturersHandler.handleDeleteManufacturer(id);
        return ResponseEntity.noContent()
                .build();
    }
}
