package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CartItemDto;
import com.service.foodorderonline.dto.ShoppingCartDto;
import com.service.foodorderonline.model.CartItem;
import com.service.foodorderonline.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    @Mapping(source = "dish.id", target = "dishId")
    @Mapping(source = "dish.name", target = "dishName")
    CartItemDto toCartItemDto(CartItem cartItem);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "cartItems", target = "cartItemDtos")
    ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart);
}
