package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Dish short response DTO")
public class DishWithSizeDto {
    private Long id;
    private String title;
    private String imageSrc;
    private Long sizeId;
    private String sizeTitle;
    private BigDecimal price;

}
