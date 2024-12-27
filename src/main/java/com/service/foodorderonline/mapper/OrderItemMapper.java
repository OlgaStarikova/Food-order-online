package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateOrderItemRequestDto;
import com.service.foodorderonline.dto.CreateOrderSideItemRequestDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.model.OrderItem;
import java.math.BigDecimal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {OrderMapperUtil.class},
        imports = {
                OrderMapperUtil.class
        })
public interface OrderItemMapper {

    OrderItemDto toOrderItemDto(OrderItem orderItem);

    List<OrderItemDto> toOrderItemDtos(List<OrderItem> orderItems);

    OrderItem toModel(CreateOrderItemRequestDto requestDto);

    @Mapping(target = "dish.id", source = "id")
    @Mapping(target = "orderItemIngreds", ignore = true, defaultValue = "new ArrayList<>()")
    @Mapping(target = "totalItemPrice", source = "price")
    OrderItem toModelFromSideItemRequest(CreateOrderSideItemRequestDto requestDto);

    @Mapping(target = "dish.id", source = "id")
    @Mapping(target = "orderItemIngreds",ignore = true, defaultValue = "new ArrayList<>()")
    @Mapping(target = "totalItemPrice", source = "price")
    List<OrderItem> toModelFromSideItemRequests(List<CreateOrderSideItemRequestDto> requestDto);

    @Named("itemTotal")
    default BigDecimal countOrderItemTotal(OrderItem orderItem) {
        return orderItem.countItemTotal();
    }
}
