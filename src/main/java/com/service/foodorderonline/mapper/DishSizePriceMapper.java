package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateDishSizePriceRequestDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.dto.DishWithSizeDto;
import com.service.foodorderonline.dto.IngredNiceDto;
import com.service.foodorderonline.model.DishSizePrice;
import com.service.foodorderonline.model.Size;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface DishSizePriceMapper {
    @Mapping(source = "size", target = "title", qualifiedByName = "setSizeTitleDto")
    @Mapping(source = "size.id", target = "sizeId")
    DishSizePriceDto toDto(DishSizePrice dishSizePrice);

    @Mapping(source = "size", target = "sizeTitle", qualifiedByName = "setSizeTitleDto")
    @Mapping(source = "size.id", target = "sizeId")
    @Mapping(source = "dish.title", target = "title")
    @Mapping(source = "dish.imageSrc", target = "imageSrc")
    @Mapping(source = "dish.id", target = "id")
    DishWithSizeDto toDishWithSizeDto(DishSizePrice dishSizePrice);

    @Mapping(source = "size", target = "title", qualifiedByName = "setSizeTitleDto")
    @Mapping(target = "itSize", constant = "true")
    @Mapping(target = "disabled", constant = "false")
    IngredNiceDto toNiceDto(DishSizePrice dishSizePrice);

    @Mapping(source = "size", target = "title", qualifiedByName = "setSizeTitleDto")
    @Mapping(target = "itSize", constant = "true")
    @Mapping(target = "disabled", constant = "false")
    List<IngredNiceDto> toNiceDtos(List<DishSizePrice> dishSizePrices);

    @Mapping(source = "size", target = "title", qualifiedByName = "setSizeTitleDto")
    List<DishSizePriceDto> toDtos(List<DishSizePrice> dishSizePrices);

    DishSizePrice toModel(CreateDishSizePriceRequestDto requestDto);

    @Named("setSizeTitleDto")
    default String getSizeTitleForDto(Size size) {
        if (size == null) {
            return null;
        }
        return size.getTitle();
    }

    @AfterMapping
    default void setDishSizePriceSize(@MappingTarget DishSizePrice dishSizePrice,
                                      CreateDishSizePriceRequestDto requestDto) {
        Size size = new Size(requestDto.getSizeId());
        dishSizePrice.setSize(size);
    }
}
