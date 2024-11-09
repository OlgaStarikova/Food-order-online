package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateDishSizePriceRequestDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.DishSizePriceMapper;
import com.service.foodorderonline.model.DishSizePrice;
import com.service.foodorderonline.repository.dish.DishSizePriceRepository;
import com.service.foodorderonline.service.DishSizePriceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DishSizePriceServiceImpl implements DishSizePriceService {
    private final DishSizePriceRepository dishSizePriceRepository;
    private final DishSizePriceMapper dishSizePriceMapper;

    @Override
    public DishSizePriceDto getById(Long id) {
        DishSizePrice dishSizePrice = dishSizePriceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find dishSizePrice by id "
                        + id));

        return dishSizePriceMapper.toDto(dishSizePrice);
    }

    @Override
    public DishSizePriceDto save(CreateDishSizePriceRequestDto requestDto) {
        DishSizePrice dishSizePrice = dishSizePriceMapper.toModel(requestDto);
        return dishSizePriceMapper.toDto(dishSizePriceRepository.save(dishSizePrice));
    }

    @Override
    public DishSizePriceDto update(Long id, CreateDishSizePriceRequestDto requestDto) {
        DishSizePrice dishSizePrice = dishSizePriceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such a dishSizePrice with id: " + id
                ));
        return dishSizePriceMapper.toDto(dishSizePriceRepository.save(dishSizePrice));
    }

    @Override
    public void deleteById(Long id) {
        dishSizePriceRepository.deleteById(id);
    }

    @Override
    public List<DishSizePriceDto> findsByDishId(Long id) {
        return dishSizePriceRepository
                .findDishSizePricesByDishId(id).stream()
                .map(dishSizePriceMapper::toDto)
                .toList();
    }
}
