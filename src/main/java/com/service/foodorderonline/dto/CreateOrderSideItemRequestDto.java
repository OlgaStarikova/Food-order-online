package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CreateOrderSideItemRequestDto(
        @Positive
        Long id,
        @Positive
        int quantity,
        @Min(0)
        BigDecimal price,
        String title
) {
}
