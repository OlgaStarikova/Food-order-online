package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Dish Ingredient response DTO")
public class DishIngredDto {
    private Long id;
    private Long ingredId;
    private String title;
    private String measure;
    private BigDecimal price;
    private String ingredCategoryName;
    private boolean selected;

    public DishIngredDto() {
    }

    public DishIngredDto(Long id) {
        this.id = id;
    }
}
