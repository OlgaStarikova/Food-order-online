package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Dish response DTO")
public class DishNiceDto {
    private Long id;
    private String title;
    private int timecook;
    private String description;
    private String imageSrc;
    private List<DishSizePriceDto> dishSizePriceDtos;
    private List<IngredNiceDto> defaultIngredDtos;
    private List<IngredCategoryWithIngredsDto> categoryWithIngredsDtos;
}
