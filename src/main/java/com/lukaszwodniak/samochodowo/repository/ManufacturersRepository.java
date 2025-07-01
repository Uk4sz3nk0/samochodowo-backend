package com.lukaszwodniak.samochodowo.repository;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ManufacturersRepository
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

@Repository
public interface ManufacturersRepository extends JpaRepository<Manufacturer, UUID> {

    Page<Manufacturer> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
}
