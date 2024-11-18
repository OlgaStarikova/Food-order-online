package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "ShoppingCart response DTO")
public record ShoppingCartDto(
        Long id,
        Long userId,
        List<CartItemDto> cartItemDtos
) {
}
