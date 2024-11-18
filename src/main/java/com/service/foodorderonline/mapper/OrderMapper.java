package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.OrderItemDto;
import com.service.foodorderonline.model.CartItem;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.model.Order;
import com.service.foodorderonline.model.OrderItem;
import com.service.foodorderonline.model.OrderItemIngred;
import com.service.foodorderonline.model.ShoppingCart;
import java.math.BigDecimal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(source = "dish.id", target = "dishId")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItemDtos")
    @Mapping(source = "status", target = "status", qualifiedByName = "status")
    OrderDto toOrderDto(Order order);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItemDtos")
    List<OrderDto> toOrdersDto(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "total", source = "cart.cartItems", qualifiedByName = "total")
    @Mapping(target = "orderItems", source = "cart.cartItems",
            qualifiedByName = "orderItemFromCartItem")
    Order cartToOrder(ShoppingCart cart, String shippingAddress);

    @Named("total")
    default BigDecimal getTotal(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(i -> i.countItemTotal().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Named("price")
    default BigDecimal getPrice(CartItem cartItem) {
        return cartItem.countItemTotal();
    }

    @Named("status")
    default String getStatus(Order.Status status) {
        return status.toString();
    }

    @Named("orderItemFromCartItem")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalItem", source = "cartItem", qualifiedByName = "price")
    @Mapping(target = "orderItemIngreds", source = "cartItem.ingreds",
            qualifiedByName = "orderItemIngredFromCartItemIngred")
    OrderItem toOrderItemFromCartItem(CartItem cartItem);

    @Named("orderItemIngredFromCartItemIngred")
    OrderItemIngred toOrderItemIngredFromCartItemIngred(Ingred ingred);
}
