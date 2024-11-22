package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "DishSizePrice response DTO")
public class DishSizePriceDto {
    private Long id;
    private Long sizeId;
    private String title;
    private BigDecimal price;

    public DishSizePriceDto(Long id) {
        this.id = id;
    }
}
