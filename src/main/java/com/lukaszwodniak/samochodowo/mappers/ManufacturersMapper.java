package com.lukaszwodniak.samochodowo.mappers;

import com.lukaszwodniak.samochodowo.models.dto.ManufacturerDto;
import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

/**
 * ManufacturersMapper
 * <br>
 * Created on: 2025-07-01
 *
 * @author Łukasz Wodniak
 */

@Mapper
public abstract class ManufacturersMapper {

    public static final ManufacturersMapper INSTANCE = Mappers.getMapper(ManufacturersMapper.class);

    public Page<ManufacturerDto> manufacturersToDto(final Page<Manufacturer> manufacturers) {
        return manufacturers.map(this::manufacturerDto);
    }

    public abstract ManufacturerDto manufacturerDto(final Manufacturer manufacturer);

    @Mapping(target = "models", ignore = true)
    public abstract Manufacturer fromDto(final ManufacturerDto manufacturerDto);

}
