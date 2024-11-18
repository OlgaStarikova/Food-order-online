package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "CategoryWithDishes response DTO")
public record CategoryWithDishesDto(
        String name,
        List<DishShortDto> dishShortDtos
) {
}
