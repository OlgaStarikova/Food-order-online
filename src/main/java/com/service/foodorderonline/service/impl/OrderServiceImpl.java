package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateOrderRequestDto;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.dto.UpdateOrderRequestDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.exception.OrderProcessingException;
import com.service.foodorderonline.mapper.OrderItemMapper;
import com.service.foodorderonline.mapper.OrderMapper;
import com.service.foodorderonline.model.Order;
import com.service.foodorderonline.model.OrderItem;
import com.service.foodorderonline.model.ShoppingCart;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.repository.order.CartItemRepository;
import com.service.foodorderonline.repository.order.OrderItemRepository;
import com.service.foodorderonline.repository.order.OrderRepository;
import com.service.foodorderonline.repository.order.ShoppingCartRepository;
import com.service.foodorderonline.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public OrderDto save(User user, CreateOrderRequestDto requestDto) {
        ShoppingCart cart = cartRepository.findShoppingCartByUserId(user.getId());
        if (cart.getCartItems().isEmpty()) {
            throw new OrderProcessingException("Cart is empty for user: " + user.getEmail());
        }
        Order order = orderMapper.cartToOrder(cart, requestDto.shippingAddress());
        order.getOrderItems().replaceAll(i -> {
            i.setOrder(order);
            return i;
        });
        Order orderSaved = orderRepository.save(order);
        cartItemRepository.deleteAllByShoppingCartId(cart.getId());
        cart.clearCart();
        return orderMapper.toOrderDto(orderSaved);
    }

    @Override
    public OrderDto update(Long orderId, UpdateOrderRequestDto updateOrderRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Order with id" + orderId + "is not found"));
        order.setStatus(Order.Status.valueOf(updateOrderRequestDto.status()));
        orderRepository.save(order);
        return orderMapper.toOrderDto(order);
    }

    @Override
    public List<OrderDto> getOrdersByUser(User user, Pageable pageable) {
        return orderMapper.toOrdersDto(
                orderRepository.findOrdersByUserId(user.getId(), pageable));
    }

    @Override
    public List<OrderItemDto> getOrderItems(User user, Long orderId) {
        return orderItemMapper.toOrderItemDtos(
                orderItemRepository.findOrderItemsByOrderIdAndUserId(orderId, user.getId()));
    }

    @Override
    public OrderItemDto getOrderItem(User user, Long orderId, Long itemId) {
        OrderItem orderIem = orderItemRepository
                .findByIdAndOrderIdAndUserId(itemId, orderId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "OrderItem is not found"
                                + " for itemId = " + itemId + " and "
                                + " order with orderId = " + orderId + " and "
                                + " user with email = " + user.getEmail()));
        return orderItemMapper.toOrderItemDto(orderIem);
    }
}
