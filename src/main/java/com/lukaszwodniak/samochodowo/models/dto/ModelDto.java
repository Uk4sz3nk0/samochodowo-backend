package com.lukaszwodniak.samochodowo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * ModelDto
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

public record ModelDto(UUID id, @NotBlank @Size(min = 1, max = 50) String name, @NotNull UUID manufacturerId,
                       String generation) {
}
