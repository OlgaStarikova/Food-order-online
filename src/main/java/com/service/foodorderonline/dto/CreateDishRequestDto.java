package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDishRequestDto {
    @NotBlank
    private String title;
    @Min(0)
    private int timecook;
    private String description;
    private String imageSrc;
    private Long categoryId;
    private boolean itConstructor;
    private List<CreateDishIngredRequestDto> createDishIngredRequestDtos;
    private List<CreateDishSizePriceRequestDto> createDishSizePriceDtos;
}
