package com.lukaszwodniak.samochodowo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * ManufacturerDto
 * <br>
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

public record ManufacturerDto(UUID id, @NotBlank @Size(min = 3, max = 60) String name) {
}
