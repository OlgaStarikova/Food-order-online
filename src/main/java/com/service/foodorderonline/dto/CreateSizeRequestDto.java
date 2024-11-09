package com.service.foodorderonline.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSizeRequestDto(
        @NotBlank
        String name
) {
}
