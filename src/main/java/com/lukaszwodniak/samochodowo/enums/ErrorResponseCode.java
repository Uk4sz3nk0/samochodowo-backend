package com.lukaszwodniak.samochodowo.enums;

/**
 * ErrorResponseCode
 * <br>
 * Created on: 2025-07-01
 *
 * @author Łukasz Wodniak
 */

public enum ErrorResponseCode implements CustomResponseCode {

    NOT_KNOWN,
    NO_SUCH_MANUFACTURER,
    MANUFACTURER_ALREADY_EXISTS,
    NO_SUCH_MODEL,
    MODEL_ALREADY_EXISTS;


    @Override
    public int code() {
        return 1000 + this.ordinal();
    }
}

interface CustomResponseCode {

    int code();
}