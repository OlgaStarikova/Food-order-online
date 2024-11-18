package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.CreateIngredRequestDto;
import com.service.foodorderonline.dto.IngredDto;
import com.service.foodorderonline.dto.IngredNiceDto;
import com.service.foodorderonline.model.Ingred;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

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

    @Mapping(source = "ingred.id", target = "ingredId")
    List<IngredNiceDto> toNiceDtos(List<Ingred> ingreds);

    @Mapping(source = "ingredcategoryId", target = "ingredCategory",
            qualifiedByName = "ingredCategoryById")
    void updateIngredFromDto(CreateIngredRequestDto ingredDto,
                             @MappingTarget Ingred ingred);

    @Named("ingredById")
    default Ingred ingredById(Long id) {
        return Optional.ofNullable(id)
                .map(Ingred::new)
                .orElse(null);
    }
}
