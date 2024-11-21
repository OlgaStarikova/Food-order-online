package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.IngredCategoryWithIngredsDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface DishService {
    DishDto save(CreateDishRequestDto requestDto);

    List<DishDto> findAll(Pageable pageable);

    DishNiceDto findById(Long id);

    void deleteDish(Long id);

    DishDto updateDish(Long id, CreateDishRequestDto requestDto);

    List<IngredCategoryWithIngredsDto> findIngredsById(Long id);
}
