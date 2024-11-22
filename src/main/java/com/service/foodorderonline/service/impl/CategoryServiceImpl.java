package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CategoryDto;
import com.service.foodorderonline.dto.CategoryWithDishesDto;
import com.service.foodorderonline.dto.CreateCategoryRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.DishWithSizesDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.CategoryMapper;
import com.service.foodorderonline.mapper.DishMapper;
import com.service.foodorderonline.model.Category;
import com.service.foodorderonline.repository.CategoryRepository;
import com.service.foodorderonline.repository.dish.DishRepository;
import com.service.foodorderonline.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final DishMapper dishMapper;
    private final DishRepository dishRepository;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such a category with id: " + id
                ));
        categoryMapper.updateCategoryFromDto(requestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<DishDto> findsByCategoryId(Long id) {
        return dishRepository.findDishesByCategoryId(id).stream()
                .map(dishMapper::toDto)
                .toList();
    }

    @Override
    public List<CategoryWithDishesDto> getAllCategoriesWithDishes(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(c -> {
                    CategoryWithDishesDto withDishesDto = new CategoryWithDishesDto(c.getName(),
                            dishMapper.toShortDtos(dishRepository
                                    .findDishesByCategoryId(c.getId())));
                    return withDishesDto;
                })
                .toList();
    }

    @Override
    public List<DishWithSizesDto> findDihesByCategoryId(Long id) {
        return dishRepository.findDishesByCategoryId(id).stream()
                .filter(d -> d.isItConstructor() == false)
                .map(dishMapper::toWithSizeDto)
                .toList();
    }

    @Override
    public List<DishNiceDto> findConstructorDishesByCategoryId(Long id) {
        return dishRepository.findDishesByCategoryId(id).stream()
                .filter(d -> d.isItConstructor() == true)
                .map(dishMapper::toNiceDto)
                .toList();
    }
}
