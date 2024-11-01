package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDishRequestDto {
    @NotBlank
    private String name;
    @Min(0)
    private int timecook;
    @Min(0)
    private BigDecimal pricelittle;
    @Min(0)
    private BigDecimal pricemiddle;
    @Min(0)
    private BigDecimal pricelarge;
    private String description;
    private String coverImage;
    private Long categoryId;
    private Set<Long> ingredIds;
}
