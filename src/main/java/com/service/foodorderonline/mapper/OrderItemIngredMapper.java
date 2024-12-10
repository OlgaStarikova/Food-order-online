package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.OrderItemIngredDto;
import com.service.foodorderonline.model.OrderItemIngred;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemIngredMapper {
    OrderItemIngredDto toOrderItemIngredDto(OrderItemIngred orderItemIngred);

    @Mapping(source = "dish.id", target = "dishId")
    List<OrderItemIngredDto> toOrderItemDtos(List<OrderItemIngred> orderItemIngreds);

    /*@Mapping(source = "createOrderItemRequest.ingredIds", target = "orderItem.orderItemIngreds",
    qualifiedByName = "orderitemsfromids")
    List<OrderItemIngred> toModel(List<Long> ingredIds);*/
}
