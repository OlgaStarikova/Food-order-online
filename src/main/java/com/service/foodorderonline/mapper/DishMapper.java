package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.model.DishSizePrice;
import com.service.foodorderonline.model.Ingred;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = CategoryMapper.class)
public interface DishMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "dishSizePrices", target = "dishSizePriceDtos",
            qualifiedByName = "setDishSizePriceDtoses")
    @Mapping(source = "ingreds", target = "ingredDtos")
    DishDto toDto(Dish dish);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryById")
    Dish toModel(CreateDishRequestDto requestDto);

    @Mapping(source = "dish.id", target = "dishId")
    List<DishDto> toDtos(List<Dish> dishes);

    @AfterMapping
    default void setIngreds(@MappingTarget Dish dish, CreateDishRequestDto requestDto) {
        if (requestDto.getIngredIds() != null) {
            List<Ingred> ingreds = requestDto.getIngredIds().stream()
                    .map(Ingred::new)
                    .collect(Collectors.toList());
            dish.setIngreds(ingreds);
        }
    }

    @Named("setDishSizePriceDtoses")
    default List<DishSizePriceDto> setDishSizePriceDto(List<DishSizePrice> dishSizePrices) {
        if (dishSizePrices == null) {
            return null;
        } else {
            List<DishSizePriceDto> list1 = new ArrayList<>();
            Iterator var3 = dishSizePrices.iterator();

            while (var3.hasNext()) {
                DishSizePrice dishSizePrice = (DishSizePrice) var3.next();
                list1.add(this.dishSizePriceToDishSizePriceDto(dishSizePrice));
            }

            return list1;
        }
    }

    default DishSizePriceDto dishSizePriceToDishSizePriceDto(DishSizePrice dishSizePrice) {
        if (dishSizePrice == null) {
            return null;
        } else {
            Long id = null;
            if (dishSizePrice.getId() != null) {
                id = dishSizePrice.getId();
            }

            DishSizePriceDto dishSizePriceDto = new DishSizePriceDto(id);
            if (dishSizePrice.getPrice() != null) {
                dishSizePriceDto.setPrice(dishSizePrice.getPrice());
            }

            if (dishSizePrice.getSize() != null) {
                dishSizePriceDto.setSizeName(dishSizePrice.getSize().getName());
            }

            return dishSizePriceDto;
        }
    }
}
