package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateDishSizePriceRequestDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import java.util.List;

public interface DishSizePriceService {

    DishSizePriceDto getById(Long id);

    DishSizePriceDto save(CreateDishSizePriceRequestDto createDishSizePriceRequestDto);

    DishSizePriceDto update(Long id, CreateDishSizePriceRequestDto createDishSizePriceRequestDto);

    void deleteById(Long id);

    List<DishSizePriceDto> findsByDishId(Long id);
}
