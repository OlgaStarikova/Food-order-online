package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "OrderItem response DTO")
public record OrderItemDto(
        Long id,
        Long dishId,
        int quantity,
        String title,
        BigDecimal totalItemPrice,
        List<OrderItemIngredDto> orderItemIngredDtos
) {
}
