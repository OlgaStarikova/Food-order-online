package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.CreateIngredRequestDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.exception.EntityNotFoundException;
import com.service.foodorderonline.mapper.IngredMapper;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.repository.IngredRepository;
import com.service.foodorderonline.service.IngredService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredServiceImpl implements IngredService {
    private final IngredRepository ingredRepository;
    private final IngredMapper ingredMapper;

    @Override
    public List<IngredDto> findAll(Pageable pageable) {
        return ingredRepository.findAll(pageable).stream()
                .map(ingredMapper::toDto)
                .toList();
    }

    @Override
    public IngredDto getById(Long id) {
        Ingred ingred = ingredRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find ingred by id " + id));
        return ingredMapper.toDto(ingred);
    }

    @Override
    public IngredDto save(CreateIngredRequestDto requestDto) {
        Ingred ingred = ingredMapper.toModel(requestDto);
        return ingredMapper.toDto(ingredRepository.save(ingred));
    }

    @Override
    public IngredDto update(Long id, CreateIngredRequestDto requestDto) {
        Ingred ingred = ingredRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No such a ingred with id: " + id
                ));
        ingredMapper.updateIngredFromDto(requestDto, ingred);
        return ingredMapper.toDto(ingredRepository.save(ingred));
    }

    @Override
    public void deleteById(Long id) {
        ingredRepository.deleteById(id);
    }
}
