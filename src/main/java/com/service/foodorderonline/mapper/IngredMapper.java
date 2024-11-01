package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateIngredRequestDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.model.Ingred;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = IngredCategoryMapper.class)
public interface IngredMapper {
    @Mapping(source = "ingredCategory.id", target = "ingredcategoryId")
    IngredDto toDto(Ingred ingred);

    @Mapping(source = "ingredcategoryId", target = "ingredCategory",
            qualifiedByName = "ingredCategoryById")
    Ingred toModel(CreateIngredRequestDto createIngredRequestDto);

    @Mapping(source = "ingredCategory.id", target = "ingredcategoryId")
    @Mapping(source = "ingred.id", target = "ingredId")
    List<IngredDto> toDtos(List<Ingred> ingreds);

    void updateIngredFromDto(CreateIngredRequestDto ingredDto,
                             @MappingTarget Ingred ingred);
}
