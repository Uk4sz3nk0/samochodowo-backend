package com.lukaszwodniak.samochodowo.errors.models;

import com.lukaszwodniak.samochodowo.enums.ErrorResponseCode;
import com.lukaszwodniak.samochodowo.models.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ModelsExceptionHandler
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

@Slf4j
@ControllerAdvice
public class ModelsExceptionHandler {

    @ExceptionHandler(NoSuchModelException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchModelException(final NoSuchModelException e) {
        log.error("Handling NoSuchModelException", e);
        var response = new ApiErrorResponse(ErrorResponseCode.NO_SUCH_MODEL, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(ModelAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleModelAlreadyExistsException(final ModelAlreadyExistsException e) {
        log.error("Handling ModelAlreadyExistsException", e);
        var response = new ApiErrorResponse(ErrorResponseCode.MODEL_ALREADY_EXISTS, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }
}
