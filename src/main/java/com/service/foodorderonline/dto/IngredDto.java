package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Ingredient response DTO")
public class IngredDto {
    private String name;
    private String measure;
    private BigDecimal price;
    private String description;
    private String coverImage;
    private int calories;
    private int proteins;
    private int fats;
    private int carbogydrates;
    private Long ingredcategoryId;
}
