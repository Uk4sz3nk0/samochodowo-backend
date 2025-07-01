package com.lukaszwodniak.samochodowo.errors.manufacturers;

/**
 * ManufacturerAlreadyExistsException
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */
public class ManufacturerAlreadyExistsException extends RuntimeException {

    public ManufacturerAlreadyExistsException(String name) {
        super(String.format("Manufacturer %s already exists", name));
    }
}
