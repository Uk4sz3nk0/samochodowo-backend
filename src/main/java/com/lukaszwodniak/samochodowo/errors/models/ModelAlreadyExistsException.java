package com.lukaszwodniak.samochodowo.errors.models;

/**
 * ModelAlreadyExistsException
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */
public class ModelAlreadyExistsException extends RuntimeException {

    public ModelAlreadyExistsException(String name, String manufacturerName) {
        super(String.format("Model with name %s already exists for %s", name, manufacturerName));
    }
}
