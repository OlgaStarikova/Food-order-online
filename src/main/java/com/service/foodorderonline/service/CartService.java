package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateCartItemRequestDto;
import com.service.foodorderonline.dto.ShoppingCartDto;
import com.service.foodorderonline.dto.UpdateCartItemRequestDto;
import com.service.foodorderonline.model.User;

public interface CartService {
    ShoppingCartDto save(User user, CreateCartItemRequestDto requestDto);

    ShoppingCartDto update(
            User user, Long id, UpdateCartItemRequestDto updateCartItemRequestDto);

    void deleteById(Long id);

    ShoppingCartDto getShoppingCartByUser(User user);

    void createShoppingCart(User user);
}
