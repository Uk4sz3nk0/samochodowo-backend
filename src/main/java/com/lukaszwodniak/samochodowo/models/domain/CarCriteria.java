package com.lukaszwodniak.samochodowo.models.domain;

/**
 * CarCriteria
 * <br>
 * Created on: 2025-06-22
 *
 * @author Łukasz Wodniak
 */

public record CarCriteria(String manufacturer, String model, CarSearchParams params) {

    public CarCriteria(CarSearchParams params) {
        this(null, null, params);
    }

    public CarCriteria(String manufacturer, CarSearchParams params) {
        this(manufacturer, null, params);
    }

}
