package com.lukaszwodniak.samochodowo.models.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * CarDto
 * <br>
 * Created on: 2025-06-20
 *
 * @author Łukasz Wodniak
 */

public record CarDto(UUID id, String manufacturer, String model, short engineSize, short power,
                     String shortDescription, long distance, String fuel, String gearbox, short productionYear,
                     String city, String voivodeship, BigDecimal price, String publisherType) {
}
