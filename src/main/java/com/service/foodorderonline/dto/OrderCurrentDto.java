package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Order response DTO")
public record OrderCurrentDto(
        Long id,
        String status
) {
}
