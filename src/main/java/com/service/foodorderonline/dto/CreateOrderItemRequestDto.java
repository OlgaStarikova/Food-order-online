package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public record CreateOrderItemRequestDto(
        @Positive
        Long dishId,
        @Positive
        int count,
        @Min(0)
        BigDecimal price,
        String title,
        Long sizeId,
        List<Long> ingredIds
) {
}
