package com.service.foodorderonline.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateIngredCategoryRequestDto(
        @NotBlank
        String name,
        String description,
        int sortnumber
) {
}
