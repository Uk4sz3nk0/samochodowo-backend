package com.lukaszwodniak.samochodowo.errors;

import com.lukaszwodniak.samochodowo.enums.ErrorResponseCode;
import com.lukaszwodniak.samochodowo.errors.manufacturers.ManufacturerAlreadyExistsException;
import com.lukaszwodniak.samochodowo.errors.manufacturers.NoSuchManufacturerException;
import com.lukaszwodniak.samochodowo.models.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalErrorHandler
 * <br>
 * Created on: 2025-07-01
 *
 * @author Lukas
 */

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NoSuchManufacturerException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchManufacturerException(final NoSuchManufacturerException e) {
        log.error("Handling NoSuchManufacturerException", e);
        var response = new ApiErrorResponse(ErrorResponseCode.NO_SUCH_MANUFACTURER, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(ManufacturerAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleManufacturerAlreadyExistsException(final ManufacturerAlreadyExistsException e) {
        log.error("Handling ManufacturerAlreadyExistsException", e);
        var response = new ApiErrorResponse(ErrorResponseCode.MANUFACTURER_ALREADY_EXISTS, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
