package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateIngredRequestDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.model.Ingred;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface IngredMapper {
    IngredDto toDto(Ingred category);

    Ingred toModel(CreateIngredRequestDto createIngredRequestDto);

    void updateIngredFromDto(CreateIngredRequestDto ingredDto,
                             @MappingTarget Ingred ingred);

    @AfterMapping
    default void setMeasure(@MappingTarget Ingred ingred,CreateIngredRequestDto requestDto) {
        if (requestDto.getMeasure() != null) {
            ingred.setMeasure(Ingred.UnitOfMeasure.valueOf(requestDto.getMeasure()));
        }
    }

    @AfterMapping
    default void setMeasureValue(@MappingTarget IngredDto ingredDto,Ingred ingred) {
        if (ingred.getMeasure() != null) {
            ingredDto.setMeasure(ingred.getMeasure().getUnit());
        }
    }
}
