package com.lukaszwodniak.samochodowo.handlers.mock;

import com.lukaszwodniak.samochodowo.enums.Fuel;
import com.lukaszwodniak.samochodowo.enums.Gearbox;
import com.lukaszwodniak.samochodowo.enums.PublisherType;
import com.lukaszwodniak.samochodowo.handlers.CarsHandler;
import com.lukaszwodniak.samochodowo.models.domain.CarCriteria;
import com.lukaszwodniak.samochodowo.models.dto.CarDto;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * CarsHandlerMock
 * <br>
 * Created on: 2025-06-20
 *
 * @author Łukasz Wodniak
 */

@Service
@Primary
public class CarsHandlerMock implements CarsHandler {

    @Override
    public Page<CarDto> handleGetCars(CarCriteria carCriteria, Pageable pageable) {
        List<CarDto> cars;
        if (carCriteria.manufacturer() != null && carCriteria.model() == null) {
            cars = getCarsByManufacturer(carCriteria);
        } else if (carCriteria.model() != null) {
            cars = getCarsByManufacturer(carCriteria).stream()
                    .filter(car -> car.model()
                            .equalsIgnoreCase(carCriteria.model()))
                    .toList();
        } else {
            cars = getMockCars();
        }
        return new PageImpl<>(cars, pageable, cars.size());
    }

    @Override
    public long handleCountCars(CarCriteria carCriteria) {
        var cars = handleGetCars(carCriteria, Pageable.unpaged());
        return cars.getTotalElements();
    }

    private List<CarDto> getCarsByManufacturer(CarCriteria carCriteria) {
        return getMockCars().stream()
                .filter(car -> car.manufacturer()
                        .equalsIgnoreCase(carCriteria.manufacturer()))
                .toList();
    }

    private List<CarDto> getMockCars() {
        return List.of(
                new CarDto(UUID.randomUUID(), "Fiat", "Panda", (short) 1199, (short) 54, "Panda VAN 4x4 Sprawna!", 219455, Fuel.LPG.name(), Gearbox.MANUAL.name(), (short) 2001, "Chrzanów", "Śląskie", BigDecimal.valueOf(2500L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Suzuki", "Swift", (short) 1299, (short) 94, null, 219455, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2007, "Kęty", "Małopolskie", BigDecimal.valueOf(15000), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Seat", "Altea XL", (short) 1899, (short) 105, "Zadbane * igła", 219455, Fuel.DIESEL.name(), Gearbox.MANUAL.name(), (short) 2009, "Katowice", "Śląskie", BigDecimal.valueOf(19799L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Toyota", "Yaris", (short) 998, (short) 69, "Małe spalanie", 150000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2012, "Warszawa", "Mazowieckie", BigDecimal.valueOf(18900L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Volkswagen", "Golf", (short) 1598, (short) 105, "Do lekkich poprawek lakierniczych", 230000, Fuel.DIESEL.name(), Gearbox.MANUAL.name(), (short) 2010, "Wrocław", "Dolnośląskie", BigDecimal.valueOf(23000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Opel", "Astra", (short) 1399, (short) 90, "Pierwszy właściciel", 170000, Fuel.GAS.name(), Gearbox.AUTOMATIC.name(), (short) 2014, "Poznań", "Wielkopolskie", BigDecimal.valueOf(27000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Ford", "Focus", (short) 1997, (short) 125, null, 210000, Fuel.DIESEL.name(), Gearbox.MANUAL.name(), (short) 2008, "Łódź", "Łódzkie", BigDecimal.valueOf(16500L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Renault", "Clio", (short) 1149, (short) 75, "Zadbany, bezwypadkowy", 140000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2015, "Kraków", "Małopolskie", BigDecimal.valueOf(20000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Skoda", "Octavia", (short) 1968, (short) 150, "Bogata wersja", 180000, Fuel.DIESEL.name(), Gearbox.AUTOMATIC.name(), (short) 2017, "Gdańsk", "Pomorskie", BigDecimal.valueOf(48000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "BMW", "320d", (short) 1995, (short) 163, "Full opcja", 250000, Fuel.DIESEL.name(), Gearbox.AUTOMATIC.name(), (short) 2013, "Szczecin", "Zachodniopomorskie", BigDecimal.valueOf(52000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Mercedes", "C200", (short) 1796, (short) 184, null, 190000, Fuel.GAS.name(), Gearbox.AUTOMATIC.name(), (short) 2012, "Bydgoszcz", "Kujawsko-Pomorskie", BigDecimal.valueOf(45000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Honda", "Civic", (short) 1339, (short) 100, "Stan bardzo dobry", 160000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2011, "Lublin", "Lubelskie", BigDecimal.valueOf(23000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Mazda", "3", (short) 1598, (short) 105, "Dynamiczny silnik", 175000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2010, "Rzeszów", "Podkarpackie", BigDecimal.valueOf(21000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Peugeot", "207", (short) 1399, (short) 90, "Mały przebieg", 120000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2013, "Opole", "Opolskie", BigDecimal.valueOf(17000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Citroen", "C4", (short) 1560, (short) 110, "Wersja Exclusive", 190000, Fuel.DIESEL.name(), Gearbox.MANUAL.name(), (short) 2009, "Zielona Góra", "Lubuskie", BigDecimal.valueOf(16000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Hyundai", "i30", (short) 1396, (short) 99, null, 130000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2016, "Kielce", "Świętokrzyskie", BigDecimal.valueOf(24000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Kia", "Ceed", (short) 1591, (short) 128, "Pierwszy właściciel", 125000, Fuel.GAS.name(), Gearbox.MANUAL.name(), (short) 2015, "Olsztyn", "Warmińsko-Mazurskie", BigDecimal.valueOf(26000L), PublisherType.PRIVATE.name()),
                new CarDto(UUID.randomUUID(), "Nissan", "Qashqai", (short) 1598, (short) 130, "Zadbany SUV", 185000, Fuel.DIESEL.name(), Gearbox.MANUAL.name(), (short) 2014, "Białystok", "Podlaskie", BigDecimal.valueOf(39000L), PublisherType.PRIVATE.name())
        );
    }
}
