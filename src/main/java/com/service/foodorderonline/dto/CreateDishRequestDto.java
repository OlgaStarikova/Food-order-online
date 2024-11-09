package com.service.foodorderonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateDishRequestDto {
    @NotBlank
    private String name;
    @Min(0)
    private int timecook;
    private String description;
    private String coverImage;
    private Long categoryId;
    private List<Long> ingredIds;
    private List<Long> dishSizePriceIds;
}
