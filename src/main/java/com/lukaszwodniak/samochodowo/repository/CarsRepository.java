package com.lukaszwodniak.samochodowo.repository;

import com.lukaszwodniak.samochodowo.models.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * CarsRepository
 * <br>
 * Created on: 2025-06-24
 *
 * @author Łukasz Wodniak
 */

@Repository
public interface CarsRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {
}
