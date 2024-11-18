package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDishIngredRequestDto {
    @Positive
    private Long ingredId;
    private Boolean isdefault;
}
