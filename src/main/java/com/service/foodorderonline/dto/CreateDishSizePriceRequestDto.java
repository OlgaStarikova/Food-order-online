package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDishSizePriceRequestDto {
    @Positive
    private Long dishId;
    @Positive
    private Long sizeId;
    @Min(0)
    private BigDecimal price;
}
