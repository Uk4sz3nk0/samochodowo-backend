package com.lukaszwodniak.samochodowo.service;

import com.lukaszwodniak.samochodowo.errors.models.ModelAlreadyExistsException;
import com.lukaszwodniak.samochodowo.errors.models.NoSuchModelException;
import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.models.entity.Model;
import com.lukaszwodniak.samochodowo.repository.ModelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ModelsService
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

@Service
@RequiredArgsConstructor
public class ModelsService {

    private static final String NAME_COLUMN = "name";

    private final ModelsRepository modelsRepository;

    public Page<Model> getModels(Pageable pageable, String phrase) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(NAME_COLUMN)
                .ascending());
        return phrase != null && !phrase.isEmpty()
                ? modelsRepository.findAllByNameContainingIgnoreCase(phrase, pageRequest)
                : modelsRepository.findAll(pageRequest);
    }

    public Model getModel(UUID id) {
        return modelsRepository.findById(id)
                .orElseThrow(() -> new NoSuchModelException(id));
    }

    public Model addModel(Model model) {
        boolean exists = modelsRepository.existsByNameIgnoreCaseAndManufacturerAndGenerationIgnoreCase(model.getName(), model.getManufacturer(), model.getGeneration());
        if (!exists) {
            return modelsRepository.save(model);
        } else {
            throw new ModelAlreadyExistsException(model.getName(), model.getManufacturer()
                    .getName());
        }
    }

    public Model updateModel(Model model) {
        var existingModel = getModel(model.getId());
        existingModel.setName(model.getName());
        return modelsRepository.save(existingModel);
    }

    public void deleteModel(UUID id) {
        modelsRepository.deleteById(id);
    }

    public Page<Model> getByManufacturer(Manufacturer manufacturer, Pageable pageable) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(NAME_COLUMN)
                .ascending());
        return this.modelsRepository.findAllByManufacturer(manufacturer, pageRequest);
    }
}
