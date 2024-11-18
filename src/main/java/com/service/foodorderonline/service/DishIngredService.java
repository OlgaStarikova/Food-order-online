package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateDishIngredRequestDto;
import com.service.foodorderonline.dto.DishIngredDto;
import java.util.List;

public interface DishIngredService {

    DishIngredDto getById(Long id);

    DishIngredDto save(CreateDishIngredRequestDto createDishIngredRequestDto);

    DishIngredDto update(Long id, CreateDishIngredRequestDto createDishIngredRequestDto);

    void deleteById(Long id);

    List<DishIngredDto> findsByDishId(Long id);
}
