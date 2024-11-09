package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Size response DTO")
public record SizeDto(
        String name
) {
}
