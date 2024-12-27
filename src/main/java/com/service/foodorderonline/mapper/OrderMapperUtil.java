package com.service.foodorderonline.mapper;

import com.service.foodorderonline.dto.CreateOrderItemRequestDto;
import com.service.foodorderonline.dto.CreateOrderSideItemRequestDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.dto.OrderItemIngredDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.model.Order;
import com.service.foodorderonline.model.OrderItem;
import com.service.foodorderonline.model.OrderItemIngred;
import com.service.foodorderonline.repository.IngredRepository;
import com.service.foodorderonline.repository.dish.DishRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("OrderMapperUtil")
@Component
@RequiredArgsConstructor
public class OrderMapperUtil {
    private static final int DEFAULT_QUANTITY_OF_INGRED = 1;
    private final IngredRepository ingredRepository;
    private final DishRepository dishRepository;

    @Named("ingredIdToOrderItemIngred")
    OrderItemIngred mapIngredIdToOrderItemIngred(OrderItem orderItem, Long ingredId) {
        Ingred ingred = ingredRepository.findById(ingredId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found ingred by ingredId = " + ingredId)
                );
        return new OrderItemIngred()
                .setOrderItem(orderItem)
                .setIngredTitle(ingred.getTitle())
                .setPrice(ingred.getPrice())
                .setQuantity(DEFAULT_QUANTITY_OF_INGRED);
    }

    @Named("ingredIdsToOrderItemIngreds")
    List<OrderItemIngred> mapIngredIdsToOrderItemIngreds(OrderItem orderItem,
                                                         List<Long> ingredIds) {
        return ingredIds.stream()
                .map(i -> mapIngredIdToOrderItemIngred(orderItem, i))
                .toList();
    }

    @Named("mapCartItemRequestToOrderItem")
    public OrderItem mapCartItemRequestToOrderItem(CreateOrderItemRequestDto requestDto) {
        OrderItem orderItem = new OrderItem().setPrice(requestDto.price())
                .setQuantity(requestDto.count())
                .setTitle(requestDto.title());

        orderItem.setOrderItemIngreds(mapIngredIdsToOrderItemIngreds(orderItem,
                requestDto.ingredIds()));

        orderItem.setTotalItemPrice(orderItem.countItemTotal());

        Dish dish = dishRepository.findById(requestDto.dishId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found dish by dishId = " + requestDto.dishId())
                );
        orderItem.setDish(dish);
        return orderItem;
    }

    @Named("mapCartItemRequestsToOrderItems")
    public List<OrderItem> mapCartItemRequestsToOrderItems(List<CreateOrderItemRequestDto>
                                                                   requestDtos) {
        return requestDtos.stream()
                .map(d -> mapCartItemRequestToOrderItem(d))
                .toList();
    }

    private OrderItemIngredDto mapOrderItemIngredToOrderItemIngredDto(OrderItemIngred ingred) {
        return new OrderItemIngredDto(
                ingred.getId(),
                ingred.getOrderItem().getId(),
                ingred.getIngredTitle(),
                ingred.getQuantity(),
                ingred.getPrice());
    }

    @Named("mapOrderItemIngredsToOrderItemIngredDtos")
    public List<OrderItemIngredDto> mapOrderItemIngredsToOrderItemIngredDtos(List<OrderItemIngred>
                                                                                     ingreds) {
        return ingreds.stream()
                .map(i -> mapOrderItemIngredToOrderItemIngredDto(i))
                .toList();
    }

    private OrderItemDto mapOrderItemToOrderItemDto(OrderItem item) {
        return new OrderItemDto(
                item.getId(),
                item.getDish().getId(),
                item.getQuantity(),
                item.getTitle(),
                item.getTotalItemPrice(),
                mapOrderItemIngredsToOrderItemIngredDtos(item.getOrderItemIngreds()));
    }

    @Named("mapOrderItemsToOrderItemDtos")
    public List<OrderItemDto> mapOrderItemsToOrderItemDtos(List<OrderItem> items) {
        return items.stream()
                .map(i -> mapOrderItemToOrderItemDto(i))
                .toList();
    }

    @Named("mapSideItemRequestToOrderItem")
    public OrderItem mapSideItemRequestToOrderItem(CreateOrderSideItemRequestDto requestDto) {
        OrderItem orderItem = new OrderItem().setPrice(requestDto.price())
                .setQuantity(requestDto.quantity())
                .setTitle(requestDto.title());
        orderItem.setOrderItemIngreds(new ArrayList<>());
        orderItem.setTotalItemPrice(requestDto.price());
        return orderItem;
    }

    @Named("mapSideItemRequestsToOrderItems")
    public List<OrderItem> mapSideItemRequestsToOrderItems(List<CreateOrderSideItemRequestDto>
                                                                   requestDtos) {
        return requestDtos.stream()
                .map(d -> mapSideItemRequestToOrderItem(d))
                .toList();
    }

    @Named("countOrderTotal")
    public BigDecimal countOrderTotal(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(i -> i.getTotalItemPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Named("getStatus")
    public String getStatus(Order.Status status) {
        return status.toString();
    }

    @Named("convertToShortTime")
    public String convertToShortTime(LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        int min = localDateTime.getMinute();
        String stringHour = (hour < 10) ? "0" : "" + String.valueOf(hour);
        String stringMin = (min < 10) ? "0" : "" + String.valueOf(min);
        return stringHour + ":" + stringMin;
    }
}
