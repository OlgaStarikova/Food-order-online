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
    private String name;
    @NotBlank
    private String measure;
    @Min(1)
    private int quantity;
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
