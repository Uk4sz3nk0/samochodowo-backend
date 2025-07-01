package com.lukaszwodniak.samochodowo.service;

import com.lukaszwodniak.samochodowo.errors.manufacturers.ManufacturerAlreadyExistsException;
import com.lukaszwodniak.samochodowo.errors.manufacturers.NoSuchManufacturerException;
import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.repository.ManufacturersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ManufacturersService
 * <br>
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

@Service
@RequiredArgsConstructor
public class ManufacturersService {

    private final ManufacturersRepository manufacturersRepository;

    public Page<Manufacturer> getManufacturers(String phrase, Pageable pageable) {
        PageRequest request = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name")
                .ascending());
        return phrase != null && !phrase.isEmpty()
                ? manufacturersRepository.findAllByNameContainingIgnoreCase(phrase, request)
                : manufacturersRepository.findAll(request);
    }

    public Manufacturer getManufacturerById(UUID id) {
        return manufacturersRepository.findById(id)
                .orElseThrow(() -> new NoSuchManufacturerException(id));
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        boolean exists = manufacturersRepository.existsByNameIgnoreCase(manufacturer.getName());
        if (!exists) {
            return manufacturersRepository.save(manufacturer);
        } else {
            throw new ManufacturerAlreadyExistsException(manufacturer.getName());
        }
    }

    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        if (manufacturer.getId() == null) {
            throw new NoSuchManufacturerException();
        }
        var existingManufacturer = getManufacturerById(manufacturer.getId());
        updateManufacturer(existingManufacturer, manufacturer);
        return manufacturersRepository.save(manufacturer);
    }

    public void deleteManufacturer(UUID id) {
        // TODO: Cannot delete manufacturer with models (FK - d8a8e26c-47a4-4329-b200-c290c24b16be)
        manufacturersRepository.deleteById(id);
    }

    private void updateManufacturer(Manufacturer existingManufacturer, Manufacturer newManufacturer) {
        existingManufacturer.setName(newManufacturer.getName());
    }
}
