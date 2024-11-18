package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Set;

@Schema(description = "OrderItem response DTO")
public record OrderItemDto(
        Long id,
        Long dishId,
        int quantity,
        BigDecimal totalItem,
        Set<OrderItemIngredDto> orderItemIngredDtos
) {
}
