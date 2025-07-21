package com.lukaszwodniak.samochodowo.handlers;

import com.lukaszwodniak.samochodowo.models.dto.ManufacturerDto;
import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * ManufacturersHandler
 * <br>
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

public interface ManufacturersHandler {
    
    Page<ManufacturerDto> handleGetManufacturers(Pageable pageable, String phrase);

    ManufacturerDto handleGetManufacturerById(UUID id);

    ManufacturerDto handleAddManufacturer(ManufacturerDto manufacturer);

    ManufacturerDto handleUpdateManufacturer(ManufacturerDto manufacturer);

    void handleDeleteManufacturer(UUID id);

    Page<ModelDto> handleGetModels(UUID id, Pageable pageable);
}
