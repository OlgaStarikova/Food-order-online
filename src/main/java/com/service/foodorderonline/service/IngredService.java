package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.CreateIngredRequestDto;
import com.service.foodorderonline.dto.IngredDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IngredService {
    List<IngredDto> findAll(Pageable pageable);

    IngredDto getById(Long id);

    IngredDto save(CreateIngredRequestDto createIngredRequestDto);

    IngredDto update(Long id, CreateIngredRequestDto createIngredRequestDto);

    void deleteById(Long id);
}
