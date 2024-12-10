package com.service.foodorderonline.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record CreateOrderRequestDto(
        String firstName,
        String secondName,
        String phone,
        String expectationTimeType,
        String paymentType,
        String expectHour,
        String expectMinute,
        String day,
        @NotBlank
        String shippingAddress,
        List<CreateOrderItemRequestDto> cartItems
) {
}
