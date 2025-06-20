package com.lukaszwodniak.samochodowo.controllers;

import com.lukaszwodniak.samochodowo.handlers.CarsHandler;
import com.lukaszwodniak.samochodowo.models.domain.CarCriteria;
import com.lukaszwodniak.samochodowo.models.domain.CarSearchParams;
import com.lukaszwodniak.samochodowo.models.dto.CarDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CarsController
 * <br>
 * Created on: 2025-06-20
 *
 * @author Łukasz Wodniak
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarsController {

    private final CarsHandler carsHandler;

    @GetMapping
    ResponseEntity<Page<CarDto>> getCars(@ModelAttribute CarSearchParams params, Pageable pageable) {
        log.debug("Endpoint \"getCars\" has called");
        var cars = carsHandler.handleGetCars(new CarCriteria(params), pageable);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{manufacturer}")
    ResponseEntity<Page<CarDto>> getCars(
            @PathVariable String manufacturer,
            @ModelAttribute CarSearchParams params,
            Pageable pageable
    ) {
        log.debug("Endpoint \"getCars\" with manufacturer \"{}\" has called", manufacturer);
        var carCriteria = new CarCriteria(manufacturer, params);
        var cars = carsHandler.handleGetCars(carCriteria, pageable);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{manufacturer}/{model}")
    ResponseEntity<Page<CarDto>> getCars(
            @PathVariable String manufacturer,
            @PathVariable String model,
            @ModelAttribute CarSearchParams params,
            Pageable pageable
    ) {
        log.debug("Endpoint \"getCars\" with manufacturer \"{}\" and model \"{}\" has called", manufacturer, model);
        var carCriteria = new CarCriteria(manufacturer, model, params);
        var cars = carsHandler.handleGetCars(carCriteria, pageable);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/count")
    ResponseEntity<Long> countCars(@ModelAttribute CarSearchParams params) {
        log.debug("Endpoint \"countCars\" has called");
        long carsCount = carsHandler.handleCountCars(new CarCriteria(params));
        return ResponseEntity.ok(carsCount);
    }

    @GetMapping("/count/{manufacturer}")
    ResponseEntity<Long> countCars(@PathVariable String manufacturer, @ModelAttribute CarSearchParams params) {
        log.debug("Endpoint \"countCars\" of \"{}\" has called", manufacturer);
        var criteria = new CarCriteria(manufacturer, params);
        long carsCount = carsHandler.handleCountCars(criteria);
        return ResponseEntity.ok(carsCount);
    }

    @GetMapping("/count/{manufacturer}/{model}")
    ResponseEntity<Long> countCars(
            @PathVariable String manufacturer,
            @PathVariable String model,
            @ModelAttribute CarSearchParams params
    ) {
        log.debug("Endpoint \"countCars\" with manufacturer \"{}\" and model \"{}\" has called", manufacturer, model);
        var criteria = new CarCriteria(manufacturer, model, params);
        long carsCount = carsHandler.handleCountCars(criteria);
        return ResponseEntity.ok(carsCount);
    }

}
