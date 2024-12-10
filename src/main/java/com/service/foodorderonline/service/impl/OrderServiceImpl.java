package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateOrderRequestDto;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.dto.UpdateOrderRequestDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.OrderItemMapper;
import com.service.foodorderonline.mapper.OrderMapper;
import com.service.foodorderonline.model.Order;
import com.service.foodorderonline.model.OrderItem;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.repository.order.OrderItemRepository;
import com.service.foodorderonline.repository.order.OrderRepository;
import com.service.foodorderonline.repository.user.UserRepository;
import com.service.foodorderonline.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private static final Long DEFAULT_USER_ID = 2L;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderDto save(Authentication authentication, CreateOrderRequestDto requestDto) {
        User user;
        if (authentication == null) {
            user = userRepository.findById(DEFAULT_USER_ID)
                    .orElseThrow(() -> new EntityNotFoundException("Not possible"
                            + "to make order without registration"));
        } else {
            user = (User) authentication.getPrincipal();
        }

        Order order = orderMapper.toOrder(requestDto);
        order.setUser(user);

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }

        order.getOrderItems()
                .stream()
                .map(o -> {
                    o.getOrderItemIngreds().stream()
                            .map(i -> i.setOrderItem(o));
                    return o;
                });

        /*List<OrderItem> list = orderItemMapper
                .toModelFromSideItemRequests(requestDto.additionalList());
        List<OrderItemIngred> emptyIngreds = new ArrayList<OrderItemIngred>();
        for (OrderItem sideItem : list) {
            sideItem.setOrder(order);
            sideItem.setOrderItemIngreds(emptyIngreds);
            List<OrderItem> newList = order.getOrderItems();
            newList.add(sideItem);
            order.setOrderItems(newList);
        }*/

        Order orderSaved = orderRepository.save(order);

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
