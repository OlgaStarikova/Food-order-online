package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.DishMapper;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.repository.dish.DishRepository;
import com.service.foodorderonline.service.DishService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Override
    public DishDto save(CreateDishRequestDto requestDto) {
        return Optional.ofNullable(requestDto)
                .map(dishMapper::toModel)
                .map(dishRepository::save)
                .map(dishMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Input parameters can't be null"));
    }

    @Override
    public List<DishDto> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable).stream()
                .map(dishMapper::toDto)
                .toList();
    }

    @Override
    public DishDto findById(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find dish by id " + id));
        return dishMapper.toDto(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public DishDto updateDish(Long id, CreateDishRequestDto requestDto) {
        Dish dish = dishMapper.toModel(requestDto);
        dish.setId(id);
        dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find dish by id " + id));
        return dishMapper.toDto(dishRepository.save(dish));
    }
}
