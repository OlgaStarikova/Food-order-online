package com.service.foodorderonline.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateOrderRequestDto(
        @NotBlank
        String status
) {
}
