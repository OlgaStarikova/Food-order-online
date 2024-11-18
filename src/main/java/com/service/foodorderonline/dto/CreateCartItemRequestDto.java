package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

public record CreateCartItemRequestDto(
        @Positive
        Long dishId,
        @Positive
        int quantity,
        @Min(0)
        BigDecimal price,
        Set<Long> ingredIds
) {
}
