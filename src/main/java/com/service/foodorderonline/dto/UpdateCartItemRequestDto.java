package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequestDto(
        @Positive
        int quantity
) {
}
