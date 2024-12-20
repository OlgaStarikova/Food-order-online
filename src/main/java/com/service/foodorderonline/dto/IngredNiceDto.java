package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Ingredient short response DTO")
public class IngredNiceDto {
    private Long id;
    private String title;
    private String measure;
    private BigDecimal price;
    private boolean itSize;
    private boolean disabled;

    public IngredNiceDto() {
    }

    public IngredNiceDto(Long id) {
        this.id = id;
    }
}
