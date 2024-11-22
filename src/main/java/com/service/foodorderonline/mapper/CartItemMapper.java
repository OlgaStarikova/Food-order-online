package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CartItemDto;
import com.service.foodorderonline.dto.CreateCartItemRequestDto;
import com.service.foodorderonline.model.CartItem;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "dish.id", target = "dishId")
    @Mapping(source = "dish.title", target = "dishName")
    @Mapping(source = "cartItem", target = "cartItemTotal", qualifiedByName = "itemTotal")
    CartItemDto toCartItemDto(CartItem cartItem);

    CartItem toCartItemModel(CreateCartItemRequestDto createCartItemRequestDto);

    @Named("itemTotal")
    default BigDecimal countCartItemTotal(CartItem cartItem) {
        return cartItem.countItemTotal();
    }
}
