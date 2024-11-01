package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.model.Ingred;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = CategoryMapper.class)
public interface DishMapper {
    @Mapping(source = "category.id", target = "categoryId")
    DishDto toDto(Dish dish);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryById")
    Dish toModel(CreateDishRequestDto requestDto);

    @Mapping(source = "dish.id", target = "dishId")
    List<DishDto> toDtos(List<Dish> dishes);

    @AfterMapping
    default void setIngredIds(@MappingTarget DishDto dishDto, Dish dish) {
        if (dish.getIngreds() != null) {
            dishDto.setIngredIds(dish.getIngreds().stream()
                    .map(Ingred::getId)
                    .collect(Collectors.toSet()));
        }
    }

    @AfterMapping
    default void setIngreds(@MappingTarget Dish dish,CreateDishRequestDto requestDto) {
        if (requestDto.getIngredIds() != null) {
            Set<Ingred> ingreds = requestDto.getIngredIds().stream()
                    .map(Ingred::new)
                    .collect(Collectors.toSet());
            dish.setIngreds(ingreds);
        }
    }
}
