package com.lukaszwodniak.samochodowo;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;

import java.util.UUID;

public abstract class TestCommons {

    protected static final String TOYOTA_MANUFACTURER = "Toyota";
    protected static final UUID BASE_ID = UUID.fromString("949bb4ec-9c16-4524-82ab-7ddacb3580a7");

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
