package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Dish short response DTO")
public class DishShortDto {
    private Long id;
    private String name;
}
