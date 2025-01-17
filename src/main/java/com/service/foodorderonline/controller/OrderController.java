package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.CreateOrderRequestDto;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.dto.UpdateOrderRequestDto;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user/orders")
    @Operation(summary = "Get orders for current user ", description = "Get orders"
            + "with order items. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public List<OrderDto> getOrders(Authentication authentication,
                                    @ParameterObject @PageableDefault Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrdersByUser(user, pageable);
    }

    @PostMapping("/orders")
    @Operation(summary = "Create a new Order", description = "Create a new order. "
            + "Available for all.")
    public OrderDto createOrder(Authentication authentication,
                                @RequestBody @Valid CreateOrderRequestDto requestDto) {
        return orderService.save(authentication, requestDto);
    }

    @PatchMapping("/admin/orders/{orderId}")
    @Operation(summary = "Update the order status", description = "Update the order by Id."
            + "Params: id = Id of the order. Available for users.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderDto updateOrderStatus(@PathVariable Long orderId,
                                      @RequestBody @Valid UpdateOrderRequestDto requestDto) {
        return orderService.update(orderId, requestDto);
    }

    @GetMapping("/user/orders/{orderId}/items")
    @Operation(summary = "Get order items for current order ", description = "Get order items"
            + "for current order. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public List<OrderItemDto> getOrderItems(Authentication authentication,
                                            @PathVariable Long orderId) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrderItems(user, orderId);
    }

    @GetMapping("/user/orders/{orderId}/items/{itemId}")
    @Operation(summary = "Get order items for current order ", description = "Get order items"
            + "for current order. Available for registered users.")
    @PreAuthorize("hasAuthority('USER')")
    public OrderItemDto getOrderItem(Authentication authentication,
                                     @PathVariable Long orderId, @PathVariable Long itemId) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrderItem(user, orderId, itemId);
    }
}
