package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "IngredCategoryWithIngreds response DTO")
public record IngredCategoryWithIngredsDto(
        String title,
        boolean allowMultiple,
        List<IngredNiceDto> options
) {
}
