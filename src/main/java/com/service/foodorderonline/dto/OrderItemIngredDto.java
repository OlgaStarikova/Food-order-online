package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "OrderItemIngred response DTO")
public record OrderItemIngredDto(
        Long id,
        Long orderItemId,
        String ingredTitle,
        int quantity,
        BigDecimal price
) {
}
