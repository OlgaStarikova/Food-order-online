package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateIngredCategoryRequestDto;
import com.service.foodorderonline.dto.IngredCategoryDto;
import com.service.foodorderonline.dto.IngredDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IngredCategoryService {
    List<IngredCategoryDto> findAll(Pageable pageable);

    IngredCategoryDto getById(Long id);

    IngredCategoryDto save(CreateIngredCategoryRequestDto createIngredCategoryRequestDto);

    IngredCategoryDto update(Long id,
                             CreateIngredCategoryRequestDto createIngredCategoryRequestDto);

    void deleteById(Long id);

    List<IngredDto> findIngredsByCategoryId(Long id);
}
