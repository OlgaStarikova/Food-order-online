package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Set;

@Schema(description = "CartItem response DTO")
public record CartItemDto(
        Long id,
        Long dishId,
        String dishName,
        int quantity,
        BigDecimal basePrice,
        BigDecimal cartItemTotal,
        Set<Long> ingredIds
) {
}
