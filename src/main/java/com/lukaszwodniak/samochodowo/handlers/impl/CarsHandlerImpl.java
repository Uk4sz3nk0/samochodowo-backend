package com.lukaszwodniak.samochodowo.handlers.impl;

import com.lukaszwodniak.samochodowo.handlers.CarsHandler;
import com.lukaszwodniak.samochodowo.models.domain.CarCriteria;
import com.lukaszwodniak.samochodowo.models.dto.CarDto;
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

@Service
public class CarsHandlerImpl implements CarsHandler {

    @Override
    public Page<CarDto> handleGetCars(CarCriteria carCriteria, Pageable pageable) {
        return null;
    }

    @Override
    public long handleCountCars(CarCriteria carCriteria) {
        return 0;
    }
}
