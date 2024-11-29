package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(description = "CategoryWithDishes response DTO")
@Getter
@Setter
@Accessors(chain = true)
public class CategoryWithDishesDto {
    private String name;
    private List<IngredNiceDto> additionalOptions;
    private List<DishNiceDto> dishesList;
    private List<DishNiceDto> constructors;
    private List<DishWithSizeDto> sideDishesList;
}
