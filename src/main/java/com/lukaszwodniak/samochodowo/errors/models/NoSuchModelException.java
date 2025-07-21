package com.lukaszwodniak.samochodowo.errors.models;

import java.util.UUID;

/**
 * NoSuchModelException
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

public class NoSuchModelException extends RuntimeException {

    public NoSuchModelException(UUID id) {
        super("No such model exists with id " + id);
    }
}
