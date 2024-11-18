package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.model.CartItem;
import com.service.foodorderonline.model.OrderItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "dish.id", target = "dishId")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @Mapping(source = "dish.id", target = "dishId")
    List<OrderItemDto> toOrderItemDtos(List<OrderItem> orderItems);

    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItemFromCartItem(CartItem cartItem);
}
