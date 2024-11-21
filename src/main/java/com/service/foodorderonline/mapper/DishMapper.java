package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateDishRequestDto;
import com.service.foodorderonline.dto.DishDto;
import com.service.foodorderonline.dto.DishIngredDto;
import com.service.foodorderonline.dto.DishNiceDto;
import com.service.foodorderonline.dto.DishShortDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.model.Dish;
import com.service.foodorderonline.model.DishIngred;
import com.service.foodorderonline.model.DishSizePrice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = CategoryMapper.class)
public interface DishMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "dishSizePrices", target = "dishSizePriceDtos",
            qualifiedByName = "setDishSizePriceDtoses")
    @Mapping(source = "dishIngreds", target = "dishIngredDtos",
            qualifiedByName = "setDishIngredDtoses")
    DishDto toDto(Dish dish);

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "categoryById")
    Dish toModel(CreateDishRequestDto requestDto);

    @Mapping(source = "dish.id", target = "dishId")
    List<DishDto> toDtos(List<Dish> dishes);

    @Mapping(source = "dishSizePrices", target = "dishSizePriceDtos",
            qualifiedByName = "setDishSizePriceDtoses")
    DishNiceDto toNiceDto(Dish dish);

    @Mapping(source = "dish.id", target = "dishId")
    List<DishShortDto> toShortDtos(List<Dish> dishes);

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

    @Named("setDishSizePriceDto")
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
                dishSizePriceDto.setSizeId(dishSizePrice.getSize().getId());
                dishSizePriceDto.setSizeName(dishSizePrice.getSize().getName());
            }

            return dishSizePriceDto;
        }
    }

    @Named("setDishIngredDto")
    default DishIngredDto dishIngredToDishIngredDto(DishIngred dishIngred) {
        if (dishIngred == null) {
            return null;
        }

        DishIngredDto dishIngredDto = new DishIngredDto();

        if (dishIngred.getId() != null) {
            dishIngredDto.setId(dishIngred.getId());
        }
        if (dishIngred.getIngred() != null) {
            dishIngredDto.setIngredId(dishIngred.getIngred().getId());
            dishIngredDto.setName(dishIngred.getIngred().getName());
            dishIngredDto.setMeasure(dishIngred.getIngred().getMeasure());
            dishIngredDto.setPrice(dishIngred.getIngred().getPrice());
            dishIngredDto.setIngredCategoryName(dishIngred.getIngred()
                    .getIngredCategory().getName());

        }
        dishIngredDto.setSelected(dishIngred.isSelected());

        return dishIngredDto;
    }

    @Named("setDishIngredDtoses")
    default List<DishIngredDto> dishIngredListToDishIngredDtoList(List<DishIngred> list) {
        if (list == null) {
            return null;
        }

        List<DishIngredDto> list1 = new ArrayList<DishIngredDto>(list.size());
        for (DishIngred dishIngred : list) {
            list1.add(dishIngredToDishIngredDto(dishIngred));
        }

        return list1;
    }
}
