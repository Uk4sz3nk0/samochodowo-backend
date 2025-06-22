package com.lukaszwodniak.samochodowo.handlers;

import com.lukaszwodniak.samochodowo.models.domain.CarCriteria;
import com.lukaszwodniak.samochodowo.models.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * CarsHandler
 * <br>
 * Created on: 2025-06-20
 *
 * @author Łukasz Wodniak
 */

public interface CarsHandler {

    Page<CarDto> handleGetCars(CarCriteria carCriteria, Pageable pageable);
    long handleCountCars(CarCriteria carCriteria);

}
