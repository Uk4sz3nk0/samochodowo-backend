package com.lukaszwodniak.samochodowo.models.dto;

import com.lukaszwodniak.samochodowo.enums.ErrorResponseCode;

/**
 * ApiErrorResponse
 * <br>
 * Created on: 2025-07-01
 *
 * @author Łukasz Wodniak
 */

public record ApiErrorResponse(ErrorResponseCode code, String message) {
}
