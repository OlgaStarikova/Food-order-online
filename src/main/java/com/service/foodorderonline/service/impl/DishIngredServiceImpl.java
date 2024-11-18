package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateDishIngredRequestDto;
import com.service.foodorderonline.dto.DishIngredDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.DishIngredMapper;
import com.service.foodorderonline.model.DishIngred;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.repository.IngredRepository;
import com.service.foodorderonline.repository.dish.DishIngredRepository;
import com.service.foodorderonline.service.DishIngredService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DishIngredServiceImpl implements DishIngredService {
    private final DishIngredRepository dishIngredRepository;
    private final IngredRepository ingredRepository;
    private final DishIngredMapper dishIngredMapper;

    @Override
    public DishIngredDto getById(Long id) {
        DishIngred dishIngred = dishIngredRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find dishIngred by id "
                        + id));
        return dishIngredMapper.toDto(dishIngred);
    }

    @Override
    public DishIngredDto save(CreateDishIngredRequestDto requestDto) {
        DishIngred dishIngred = dishIngredMapper.toModel(requestDto);
        Ingred ingred = ingredRepository.findById(requestDto.getIngredId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find Ingred by id "
                        + requestDto.getIngredId()));
        dishIngred.setIngred(ingred);
        return dishIngredMapper.toDto(dishIngredRepository.save(dishIngred));
    }

    @Override
    public DishIngredDto update(Long id, CreateDishIngredRequestDto requestDto) {
        DishIngred dishIngred = dishIngredRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such a dishIngred with id: " + id
                ));
        return dishIngredMapper.toDto(dishIngredRepository.save(dishIngred));
    }

    @Override
    public void deleteById(Long id) {
        dishIngredRepository.deleteById(id);
    }

    @Override
    public List<DishIngredDto> findsByDishId(Long id) {
        return dishIngredRepository
                .findDishIngredsByDishId(id).stream()
                .map(dishIngredMapper::toDto)
                .toList();
    }
}
