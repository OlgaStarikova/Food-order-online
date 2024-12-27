package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateOrderRequestDto;
import com.service.foodorderonline.dto.OrderCurrentDto;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.model.Order;
import com.service.foodorderonline.model.OrderItem;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = {OrderMapperUtil.class},
        imports = {
                OrderMapperUtil.class
        })
public interface OrderMapper {

    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "status", target = "status", qualifiedByName = "getStatus")
    @Mapping(source = "orderItems", target = "orderItemDtos", qualifiedByName =
            "mapOrderItemsToOrderItemDtos")
    @Mapping(target = "time", source = "timeSchedule", qualifiedByName =
            "convertToShortTime")
    OrderDto toOrderDto(Order order);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItemDtos", qualifiedByName =
            "mapOrderItemsToOrderItemDtos")
    @Mapping(target = "time", source = "timeSchedule", qualifiedByName =
            "convertToShortTime")
    List<OrderDto> toOrdersDto(List<Order> orders);

    @Mapping(source = "status", target = "status", qualifiedByName = "getStatus")
    OrderCurrentDto toOrderCurrentDto(Order order);

    @Mapping(source = "status", target = "status", qualifiedByName = "getStatus")
    List<OrderCurrentDto> toOrderCurrentsDto(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", source = "cartItems", qualifiedByName =
            "mapCartItemRequestsToOrderItems")
    Order toOrder(CreateOrderRequestDto requestDto);

    @AfterMapping
    default void setOrderTotal(@MappingTarget Order order, CreateOrderRequestDto requestDto) {
        order.setTotal(order.countOrderTotal());
    }

}
