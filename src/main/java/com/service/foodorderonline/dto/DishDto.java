package com.service.foodorderonline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Dish response DTO")
public class DishDto {
    private Long id;
    private String name;
    private int timecook;
    private BigDecimal pricelittle;
    private BigDecimal pricemiddle;
    private BigDecimal pricelarge;
    private String description;
    private String coverImage;
    private Long categoryId;
    private Set<Long> ingredIds;
}
