package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Dish response DTO")
public class DishDto {
    private Long id;
    private String name;
    private int timecook;
    private String description;
    private String imageSrc;
    private Long categoryId;
    private List<DishIngredDto> dishIngredDtos;
    private List<DishSizePriceDto> dishSizePriceDtos;
}
