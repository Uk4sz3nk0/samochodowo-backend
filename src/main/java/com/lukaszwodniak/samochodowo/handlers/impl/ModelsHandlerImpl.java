package com.lukaszwodniak.samochodowo.handlers.impl;

import com.lukaszwodniak.samochodowo.handlers.ModelsHandler;
import com.lukaszwodniak.samochodowo.mappers.ModelsMapper;
import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.models.entity.Model;
import com.lukaszwodniak.samochodowo.service.ManufacturersService;
import com.lukaszwodniak.samochodowo.service.ModelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ModelsHandlerImpl
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

@Service
@RequiredArgsConstructor
public class ModelsHandlerImpl implements ModelsHandler {

    private final ModelsService modelsService;
    private final ManufacturersService manufacturersService;

    @Override
    public Page<ModelDto> handleGetModels(Pageable pageable, String phrase) {
        var models = modelsService.getModels(pageable, phrase);
        return ModelsMapper.INSTANCE.modelsToDto(models);
    }

    @Override
    public ModelDto handleGetModel(UUID id) {
        var model = modelsService.getModel(id);
        return ModelsMapper.INSTANCE.toDto(model);
    }

    @Override
    public ModelDto handleCreateModel(ModelDto model) {
        var modelToAdd = ModelsMapper.INSTANCE.fromDto(model);
        findAndAssignManufacturer(modelToAdd, model.manufacturerId());
        var addedModel = modelsService.addModel(modelToAdd);
        return ModelsMapper.INSTANCE.toDto(addedModel);
    }

    @Override
    public ModelDto handleUpdateModel(ModelDto model) {
        var modelToUpdate = ModelsMapper.INSTANCE.fromDto(model);
        findAndAssignManufacturer(modelToUpdate, model.manufacturerId());
        var updatedModel = modelsService.updateModel(modelToUpdate);
        return ModelsMapper.INSTANCE.toDto(updatedModel);
    }

    @Override
    public void handleDeleteModel(UUID id) {
        modelsService.deleteModel(id);
    }

    private void findAndAssignManufacturer(Model model, UUID manufacturerId) {
        Manufacturer manufacturer = manufacturersService.getManufacturerById(manufacturerId);
        model.setManufacturer(manufacturer);
    }
}
