package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateIngredCategoryRequestDto;
import com.service.foodorderonline.dto.IngredCategoryDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.IngredCategoryMapper;
import com.service.foodorderonline.mapper.IngredMapper;
import com.service.foodorderonline.model.IngredCategory;
import com.service.foodorderonline.repository.IngredCategoryRepository;
import com.service.foodorderonline.repository.IngredRepository;
import com.service.foodorderonline.service.IngredCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredCategoryServiceImpl implements IngredCategoryService {
    private final IngredCategoryRepository categoryRepository;
    private final IngredCategoryMapper categoryMapper;
    private final IngredMapper ingredMapper;
    private final IngredRepository ingredRepository;

    @Override
    public List<IngredCategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public IngredCategoryDto getById(Long id) {
        IngredCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id "
                        + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public IngredCategoryDto save(CreateIngredCategoryRequestDto requestDto) {
        IngredCategory category = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public IngredCategoryDto update(Long id, CreateIngredCategoryRequestDto requestDto) {
        IngredCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such a category with id: " + id
                ));
        categoryMapper.updateIngredCategoryFromDto(requestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<IngredDto> findIngredsByCategoryId(Long id) {
        return ingredRepository.findIngredsByIngredCategoryId(id).stream()
                .map(ingredMapper::toDto)
                .toList();
    }
}
