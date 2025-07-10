package com.lukaszwodniak.samochodowo;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;

import java.util.UUID;

public abstract class TestCommons {

    protected static final String TOYOTA_MANUFACTURER = "Toyota";

    protected Manufacturer generateManufacturer(UUID id, String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setId(id);
        return manufacturer;
    }

    protected Manufacturer getToyotaManufacturer(UUID id) {
        return generateManufacturer(id, TOYOTA_MANUFACTURER);
    }
}
