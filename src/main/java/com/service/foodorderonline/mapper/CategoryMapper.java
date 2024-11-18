package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CategoryDto;
import com.service.foodorderonline.dto.CategoryWithDishesDto;
import com.service.foodorderonline.dto.CreateCategoryRequestDto;
import com.service.foodorderonline.model.Category;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    CategoryWithDishesDto toWithDishesDto(Category category);

    Category toModel(CreateCategoryRequestDto createCategoryRequestDto);

    void updateCategoryFromDto(CreateCategoryRequestDto categoryDto,
                               @MappingTarget Category category);

    @Named("categoryById")
    default Category categoryById(Long id) {
        return Optional.ofNullable(id)
                .map(Category::new)
                .orElse(null);
    }
}
