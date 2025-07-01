package com.lukaszwodniak.samochodowo.handlers.impl;

import com.lukaszwodniak.samochodowo.handlers.ManufacturersHandler;
import com.lukaszwodniak.samochodowo.mappers.ManufacturersMapper;
import com.lukaszwodniak.samochodowo.mappers.ModelsMapper;
import com.lukaszwodniak.samochodowo.models.dto.ManufacturerDto;
import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import com.lukaszwodniak.samochodowo.service.ManufacturersService;
import com.lukaszwodniak.samochodowo.service.ModelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ManufacturerHandlerImpl
 * <br>
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

@Primary
@Service
@RequiredArgsConstructor
public class ManufacturerHandlerImpl implements ManufacturersHandler {

    private final ManufacturersService manufacturersService;
    private final ModelsService modelsService;

    @Override
    public Page<ManufacturerDto> handleGetManufacturers(Pageable pageable, String phrase) {
        var cars = manufacturersService.getManufacturers(phrase, pageable);
        return ManufacturersMapper.INSTANCE.manufacturersToDto(cars);
    }

    @Override
    public ManufacturerDto handleGetManufacturerById(UUID id) {
        var manufacturer = manufacturersService.getManufacturerById(id);
        return ManufacturersMapper.INSTANCE.manufacturerDto(manufacturer);
    }

    @Override
    public ManufacturerDto handleAddManufacturer(ManufacturerDto manufacturer) {
        var manufacturerFromClient = ManufacturersMapper.INSTANCE.fromDto(manufacturer);
        var addedManufacturer = manufacturersService.createManufacturer(manufacturerFromClient);
        return ManufacturersMapper.INSTANCE.manufacturerDto(addedManufacturer);
    }

    @Override
    public ManufacturerDto handleUpdateManufacturer(ManufacturerDto manufacturer) {
        var manufacturerFromClient = ManufacturersMapper.INSTANCE.fromDto(manufacturer);
        var addedManufacturer = manufacturersService.updateManufacturer(manufacturerFromClient);
        return ManufacturersMapper.INSTANCE.manufacturerDto(addedManufacturer);
    }

    @Override
    public void handleDeleteManufacturer(UUID id) {
        manufacturersService.deleteManufacturer(id);
    }

    @Override
    public Page<ModelDto> handleGetModels(UUID id, Pageable pageable) {
        var manufacturer = manufacturersService.getManufacturerById(id);
        var models = modelsService.getByManufacturer(manufacturer, pageable);
        return ModelsMapper.INSTANCE.modelsToDto(models);
    }
}
