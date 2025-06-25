package com.lukaszwodniak.samochodowo.mappers;

import com.lukaszwodniak.samochodowo.models.dto.CarDto;
import com.lukaszwodniak.samochodowo.models.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

/**
 * CarsMapper
 * <br>
 * Created on: 2025-06-24
 *
 * @author Łukasz Wodniak
 */

@Mapper
public abstract class CarsMapper {

    public static final CarsMapper INSTANCE = Mappers.getMapper(CarsMapper.class);

    public Page<CarDto> mapCarsToDto(Page<Car> cars) {
        return cars.map(this::mapCarToDto);
    }

    @Mapping(source = "manufacturer.name", target = "manufacturer")
    @Mapping(source = "model.name", target = "model")
    public abstract CarDto mapCarToDto(Car car);
}
