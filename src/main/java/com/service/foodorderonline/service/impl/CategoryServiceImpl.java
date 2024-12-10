package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CategoryDto;
import com.service.foodorderonline.dto.CategoryWithDishesDto;
import com.service.foodorderonline.dto.CreateCategoryRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.DishWithSizeDto;
import com.service.foodorderonline.dto.DishWithSizesDto;
import com.service.foodorderonline.dto.IngredCategoryWithIngredsDto;
import com.service.foodorderonline.dto.IngredNiceDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.CategoryMapper;
import com.service.foodorderonline.mapper.DishMapper;
import com.service.foodorderonline.mapper.DishSizePriceMapper;
import com.service.foodorderonline.mapper.IngredMapper;
import com.service.foodorderonline.model.Category;
import com.service.foodorderonline.repository.CategoryRepository;
import com.service.foodorderonline.repository.dish.DishIngredRepository;
import com.service.foodorderonline.repository.dish.DishRepository;
import com.service.foodorderonline.repository.dish.SideDishRepository;
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
    private final DishIngredRepository dishIngredRepository;
    private final DishRepository dishRepository;
    private final DishSizePriceMapper dishSizePriceMapper;
    private final IngredMapper ingredMapper;
    private final SideDishRepository sideDishRepository;

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
    public CategoryWithDishesDto getCategoryWithDishesById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category by id " + id));
        return categoryMapper.toWithDishesDto(category).setDishesList(
                        findNotConstructorDishesByCategoryId(id))
                .setConstructor(findConstructorByCategoryId(id))
                .setSideDishesList(findSideDihes());
    }

    @Override
    public List<DishWithSizesDto> findDihesByCategoryId(Long id) {
        return dishRepository.findDishesByCategoryId(id).stream()
                .filter(d -> d.isItConstructor() == false)
                .map(dishMapper::toWithSizesDto)
                .toList();
    }

    @Override
    public List<DishNiceDto> findNotConstructorDishesByCategoryId(Long id) {
        return dishRepository.findDishesByCategoryId(id).stream()
                .filter(d -> d.isItConstructor() == false)
                .map(dishMapper::toNiceDto)
                .map(d -> d.setDefaultOptions(findDefaultOptions(d.getId()))
                        .setIngredOptions(findIngredOptions(d.getId())))
                .toList();
    }

    @Override
    public DishNiceDto findConstructorByCategoryId(Long id) {
        Long constructorId = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category by id " + id)).getConstructor().getId();
        return dishMapper.toNiceDto(
                        dishRepository.findById(constructorId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "Can't find dish by id " + constructorId)))
                .setDefaultOptions(findDefaultOptions(constructorId))
                .setIngredOptions(findIngredOptions(constructorId));
    }

    private List<IngredNiceDto> findDefaultOptions(Long dishId) {
        return ingredMapper.toNiceDtos(dishIngredRepository
                .findDefaultIngredsByDishId(dishId));
    }

    private List<IngredCategoryWithIngredsDto> findIngredOptions(Long dishId) {
        return dishIngredRepository
                .findAllIngredCategoriesByDishId(dishId).stream()
                .map(c -> {
                    IngredCategoryWithIngredsDto
                            ingredCategoryWithIngredsDto =
                            new IngredCategoryWithIngredsDto(c.getName(),
                                    c.isAllowMultiple(),
                                    ingredMapper.toNiceDtos(dishIngredRepository
                                            .findAllIngredsInCategoryByDishId(
                                                    dishId, c.getId())));
                    return ingredCategoryWithIngredsDto;
                })
                .toList();
    }

    private List<DishWithSizeDto> findSideDihes() {
        return sideDishRepository.findAll().stream()
                .map(d -> d.getDishSizePrice())
                .map(dishSizePriceMapper::toDishWithSizeDto)
                .toList();
    }
}
