package com.lukaszwodniak.samochodowo.mappers;

import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import com.lukaszwodniak.samochodowo.models.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

/**
 * ModelsMapper
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

@Mapper
public abstract class ModelsMapper {

    public static ModelsMapper INSTANCE = Mappers.getMapper(ModelsMapper.class);

    public Page<ModelDto> modelsToDto(Page<Model> models) {
        return models.map(this::toDto);
    }

    @Mapping(source = "manufacturer.id", target = "manufacturerId")
    public abstract ModelDto toDto(Model model);

    @Mapping(target = "manufacturer", ignore = true)
    public abstract Model fromDto(ModelDto modelDto);
}
