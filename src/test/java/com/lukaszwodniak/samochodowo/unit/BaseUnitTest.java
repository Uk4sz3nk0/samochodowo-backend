package com.lukaszwodniak.samochodowo.unit;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {

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
