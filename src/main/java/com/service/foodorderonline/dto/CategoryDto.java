package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category response DTO")
public record CategoryDto(
        String name,
        String description,
        String coverImageMain,
        String coverImageAddl
) {
}
