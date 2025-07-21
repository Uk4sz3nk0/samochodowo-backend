package com.lukaszwodniak.samochodowo.repository;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.models.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ModelsRepository
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

@Repository
public interface ModelsRepository extends JpaRepository<Model, UUID> {

    Page<Model> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByNameIgnoreCaseAndManufacturerAndGenerationIgnoreCase(String name, Manufacturer manufacturer, String generation);

    Page<Model> findAllByManufacturer(Manufacturer manufacturer, Pageable pageable);
}
