package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.dto.OrderItemIngredDto;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.model.OrderItem;
import com.service.foodorderonline.model.OrderItemIngred;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemIngredMapper {
    OrderItemIngredDto toOrderItemIngredDto(OrderItemIngred orderItemIngred);

    @Mapping(source = "dish.id", target = "dishId")
    List<OrderItemDto> toOrderItemDtos(List<OrderItem> orderItems);

    @Mapping(source = "cartItem.ingreds", target = "orderItem.orderItemIngreds")
    List<OrderItemIngred> toOrderItemIngredsFromCartItemIngreds(List<Ingred> ingreds);
}
