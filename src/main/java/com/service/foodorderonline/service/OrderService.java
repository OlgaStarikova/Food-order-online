package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateOrderRequestDto;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.dto.UpdateOrderRequestDto;
import com.service.foodorderonline.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface OrderService {
    OrderDto save(Authentication authentication, CreateOrderRequestDto requestDto);

    OrderDto update(
            Long id, UpdateOrderRequestDto updateOrderRequestDto);

    List<OrderDto> getOrdersByUser(User user, Pageable pageable);

    List<OrderItemDto> getOrderItems(User user, Long orderId);

    OrderItemDto getOrderItem(User user, Long orderId, Long itemId);
}
