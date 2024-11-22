package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CategoryDto;
import com.service.foodorderonline.dto.CategoryWithDishesDto;
import com.service.foodorderonline.dto.CreateCategoryRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.DishWithSizesDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto);

    void deleteById(Long id);

    List<DishDto> findsByCategoryId(Long id);

    List<CategoryWithDishesDto> getAllCategoriesWithDishes(Pageable pageable);

    List<DishWithSizesDto> findDihesByCategoryId(Long id);

    List<DishNiceDto> findConstructorDishesByCategoryId(Long id);

}
