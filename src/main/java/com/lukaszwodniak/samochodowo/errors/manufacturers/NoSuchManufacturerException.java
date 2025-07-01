package com.lukaszwodniak.samochodowo.errors.manufacturers;

import java.util.UUID;

/**
 * NoSuchManufacturerException
 * <br>
 * Created on: 2025-07-01
 *
 * @author Łukasz Wodniak
 */

public class NoSuchManufacturerException extends RuntimeException {

    public NoSuchManufacturerException() {
        this("null");
    }

    public NoSuchManufacturerException(String message) {
        super(message);
    }

    public NoSuchManufacturerException(UUID id) {
        super("No such manufacturer with id: " + id);
    }
}
