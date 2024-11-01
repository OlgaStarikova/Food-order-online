package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateIngredCategoryRequestDto;
import com.service.foodorderonline.dto.IngredCategoryDto;
import com.service.foodorderonline.model.IngredCategory;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface IngredCategoryMapper {
    IngredCategoryDto toDto(IngredCategory ingredCategory);

    IngredCategory toModel(CreateIngredCategoryRequestDto createIngredCategoryRequestDto);

    void updateIngredCategoryFromDto(CreateIngredCategoryRequestDto categoryDto,
                               @MappingTarget IngredCategory category);

    @Named("ingredCategoryById")
    default IngredCategory ingredCategoryById(Long id) {
        return Optional.ofNullable(id)
                .map(IngredCategory::new)
                .orElse(null);
    }
}
