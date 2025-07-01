package com.lukaszwodniak.samochodowo.handlers.impl;

import com.lukaszwodniak.samochodowo.handlers.CarsHandler;
import com.lukaszwodniak.samochodowo.mappers.CarsMapper;
import com.lukaszwodniak.samochodowo.models.domain.CarCriteria;
import com.lukaszwodniak.samochodowo.models.dto.CarDto;
import com.lukaszwodniak.samochodowo.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * CarsHandlerImpl
 * <br>
 * Created on: 2025-06-20
 *
 * @author Łukasz Wodniak
 */

@RequiredArgsConstructor
@Service
public class CarsHandlerImpl implements CarsHandler {

    private final CarsService carsService;

    @Override
    public Page<CarDto> handleGetCars(CarCriteria carCriteria, Pageable pageable) {
        var cars = carsService.getCars(carCriteria, pageable);
        return CarsMapper.INSTANCE.mapCarsToDto(cars);
    }

    @Override
    public long handleCountCars(CarCriteria carCriteria) {
        return carsService.countCars(carCriteria);
    }
}
