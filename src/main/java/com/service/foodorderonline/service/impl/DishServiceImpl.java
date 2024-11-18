package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishIngredDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.dto.IngredCategoryWithIngredsDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.DishIngredMapper;
import com.service.foodorderonline.mapper.DishMapper;
import com.service.foodorderonline.mapper.DishSizePriceMapper;
import com.service.foodorderonline.mapper.IngredCategoryMapper;
import com.service.foodorderonline.mapper.IngredMapper;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.repository.IngredRepository;
import com.service.foodorderonline.repository.dish.DishIngredRepository;
import com.service.foodorderonline.repository.dish.DishRepository;
import com.service.foodorderonline.repository.dish.DishSizePriceRepository;
import com.service.foodorderonline.repository.dish.SizeRepository;
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
    private final DishSizePriceRepository dishSizePriceRepository;
    private final SizeRepository sizeRepository;
    private final DishIngredRepository dishIngredRepository;
    private final IngredRepository ingredRepository;
    private final DishMapper dishMapper;
    private final DishSizePriceMapper dishSizePriceMapper;
    private final DishIngredMapper dishIngredMapper;
    private final IngredMapper ingredMapper;
    private final IngredCategoryMapper ingredCategoryMapper;

    @Override
    public DishDto save(CreateDishRequestDto requestDto) {
        Dish dish = Optional.ofNullable(requestDto)
                .map(dishMapper::toModel)
                .map(dishRepository::save)
                .orElseThrow(() -> new RuntimeException("Input parameters can't be null"));

        List<DishSizePriceDto> dishSizePriceDtos = requestDto.getCreateDishSizePriceDtos()
                .stream()
                .map(dishSizePriceMapper::toModel)
                .map(d -> d.setDish(dish))
                .map(dishSizePriceRepository::save)
                .map(dishSizePriceMapper::toDto)
                .map(t -> t.setSizeName(sizeRepository.findById(t.getSizeId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Can't find Size by id " + t.getSizeId())).getName()))
                .toList();

        List<DishIngredDto> ingredDtos = requestDto.getCreateDishIngredRequestDtos()
                .stream()
                .map(dishIngredMapper::toModel)
                .map(i -> i.setDish(dish))
                .map(dishIngredRepository::save)
                .map(dishIngredMapper::toDto)
                .map(t -> {
                    Ingred ingred = ingredRepository.findById(t.getIngredId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Can't find Ingred by id " + t.getIngredId()));
                    t.setName(ingred.getName());
                    t.setMeasure(ingred.getMeasure());
                    t.setPrice(ingred.getPrice());
                    t.setIngredCategoryName(ingred.getIngredCategory().getName());
                    return t;
                })
                .toList();

        DishDto dishDto = dishMapper.toDto(dish);
        dishDto.setDishSizePriceDtos(dishSizePriceDtos);
        dishDto.setDishIngredDtos(ingredDtos);

        return dishDto;

    }

    @Override
    public List<DishDto> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable).stream()
                .map(dishMapper::toDto)
                .toList();
    }

    @Override
    public DishNiceDto findById(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find dish by id " + id));

        DishNiceDto dishNiceDto = dishMapper.toNiceDto(dish);

        dishNiceDto.setDefaultIngredDtos(ingredMapper.toNiceDtos(dishIngredRepository
                .findDefaultIngredsByDishId(id)));

        dishNiceDto.setCategoryWithIngredsDtos(dishIngredRepository
                .findIngredCategoriesByDishId(id).stream()
                .map(c -> {
                    IngredCategoryWithIngredsDto
                            ingredCategoryWithIngredsDto =
                            new IngredCategoryWithIngredsDto(c.getName(), ingredMapper
                                    .toNiceDtos(dishIngredRepository.findIngredsInCategoryByDishId(
                                            dish.getId(), c.getId())));
                    return ingredCategoryWithIngredsDto;
                })
                .toList());
        return dishNiceDto;
    }

    @Override
    public void deleteDish(Long id) {
        dishIngredRepository.deleteByDishId(id);
        dishSizePriceRepository.deleteByDishId(id);
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
