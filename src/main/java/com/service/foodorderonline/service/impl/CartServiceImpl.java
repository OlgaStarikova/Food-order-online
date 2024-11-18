package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateCartItemRequestDto;
import com.service.foodorderonline.dto.ShoppingCartDto;
import com.service.foodorderonline.dto.UpdateCartItemRequestDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.CartItemMapper;
import com.service.foodorderonline.mapper.ShoppingCartMapper;
import com.service.foodorderonline.model.CartItem;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.model.ShoppingCart;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.repository.dish.DishRepository;
import com.service.foodorderonline.repository.order.CartItemRepository;
import com.service.foodorderonline.repository.order.ShoppingCartRepository;
import com.service.foodorderonline.repository.user.UserRepository;
import com.service.foodorderonline.service.CartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final DishRepository dishRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper cartMapper;
    private final UserRepository userRepository;

    @Override
    public ShoppingCartDto save(User user, CreateCartItemRequestDto requestDto) {
        Dish dish = dishRepository.findById(requestDto.dishId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such dish with id: " + requestDto.dishId()));
        ShoppingCart shoppingCart = cartRepository.findShoppingCartByUserId(user.getId());
        Optional<CartItem> cartItemExists = shoppingCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getDish().equals(dish))
                .findFirst();
        if (cartItemExists.isPresent()) {
            CartItem cartItem = cartItemExists.get();
            cartItem.setQuantity(cartItem.getQuantity() + requestDto.quantity());
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = cartItemMapper.toCartItemModel(requestDto);
            cartItem.setShoppingCart(shoppingCart);
            cartItem.setDish(dish);
            cartItemRepository.save(cartItem);
        }
        shoppingCart.setCartItems(cartItemRepository
                .findCartItemsByShoppingCart_Id(shoppingCart.getId()));
        return cartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto update(User user, Long cartItemId,
                                  UpdateCartItemRequestDto requestDto) {
        ShoppingCart cart = cartRepository.findShoppingCartByUserId(user.getId());
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(cartItemId, cart.getId())
                .map(item -> {
                    item.setQuantity(requestDto.quantity());
                    return item;
                })
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such a cartItem with id: " + cartItemId
                ));
        cartItemRepository.save(cartItem);
        return cartMapper.toShoppingCartDto(cartRepository.findShoppingCartByUserId(user.getId()));
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    public ShoppingCartDto getShoppingCartByUser(User user) {
        return cartMapper.toShoppingCartDto(
                cartRepository.findShoppingCartByUserId(user.getId()));
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartRepository.save(shoppingCart);
    }
}
