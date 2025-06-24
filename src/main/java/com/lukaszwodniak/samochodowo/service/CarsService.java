package com.lukaszwodniak.samochodowo.service;

import com.lukaszwodniak.samochodowo.models.domain.CarCriteria;
import com.lukaszwodniak.samochodowo.models.entity.Car;
import com.lukaszwodniak.samochodowo.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * CarsService
 * <br>
 * Created on: 2025-06-24
 *
 * @author Łukasz Wodniak
 */

@RequiredArgsConstructor
@Service
public class CarsService {

    private final CarsRepository carsRepository;

    public Page<Car> getCars(final CarCriteria carCriteria, final Pageable pageable) {


        return Page.empty(); // TODO: To implement
    }

    public long countCars(final CarCriteria carCriteria) {


        return 0; // TODO: To implement
    }
}
