package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateDishSizePriceRequestDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.model.DishSizePrice;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface DishSizePriceMapper {
    @Mapping(source = "size.name", target = "sizeName")
    DishSizePriceDto toDto(DishSizePrice dishSizePrice);

    @Mapping(source = "size.name", target = "sizeName")
    List<DishSizePriceDto> toDtos(List<DishSizePrice> dishSizePrices);

    @Mapping(source = "dishId", target = "dish.id")
    @Mapping(source = "sizeId", target = "size.id")
    DishSizePrice toModel(CreateDishSizePriceRequestDto dishSizePriceDto);
}
