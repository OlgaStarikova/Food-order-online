package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateIngredRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String measure;
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
    private int calories;
    private int proteins;
    private int fats;
    private int carbogydrates;
    private Long ingredcategoryId;
}
