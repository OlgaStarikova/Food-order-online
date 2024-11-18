package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateDishIngredRequestDto;
import com.service.foodorderonline.dto.DishIngredDto;
import com.service.foodorderonline.model.DishIngred;
import com.service.foodorderonline.model.Ingred;
import com.service.foodorderonline.model.IngredCategory;
import java.util.List;
import java.util.Optional;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface DishIngredMapper {
    @Mapping(source = "ingred.id", target = "ingredId")
    @Mapping(source = "ingred.name", target = "name")
    @Mapping(source = "ingred.measure", target = "measure")
    @Mapping(source = "ingred.price", target = "price")
    @Mapping(source = "ingred.ingredCategory", target = "ingredCategoryName",
            qualifiedByName = "getIngCatName")
    DishIngredDto toDto(DishIngred dishIngred);

    @Mapping(source = "ingred.id", target = "ingredId")
    @Mapping(source = "ingred.name", target = "name")
    @Mapping(source = "ingred.measure", target = "measure")
    @Mapping(source = "ingred.price", target = "price")
    @Mapping(source = "ingred.ingredCategory", target = "ingredCategoryName",
            qualifiedByName = "getIngCatName")
    List<DishIngredDto> toDtos(List<DishIngred> dishIngreds);

    @Mapping(target = "ingred", source = "ingredId", qualifiedByName = "ingredById")
    DishIngred toModel(CreateDishIngredRequestDto requestDto);

    @Named("getIngCatName")
    default String getIngCatName(IngredCategory ingredCategory) {
        if (ingredCategory == null) {
            return null;
        }
        return ingredCategory.getName();
    }

    @AfterMapping
    default void setDishIngredIngred(@MappingTarget DishIngred dishIngred,
                               CreateDishIngredRequestDto requestDto) {
        Ingred ingred = new Ingred(requestDto.getIngredId());
        dishIngred.setIngred(ingred);
    }

    @Named("ingredById")
    default Ingred ingredById(Long id) {
        return Optional.ofNullable(id)
                .map(Ingred::new)
                .orElse(null);
    }
}
