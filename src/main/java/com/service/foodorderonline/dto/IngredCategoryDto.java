package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "IngredCategory response DTO")
public record IngredCategoryDto(
        String name,
        boolean allowMultiple,
        String description
) {
}
