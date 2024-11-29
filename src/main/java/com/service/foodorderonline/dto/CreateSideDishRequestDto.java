package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Positive;

public record CreateSideDishRequestDto(
        @Positive
        Long dishId
) {
}
