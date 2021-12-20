package com.sweetspotrecipies.sweetspot.api.mapper;

import com.sweetspotrecipies.sweetspot.api.model.IngredientDTO;
import com.sweetspotrecipies.sweetspot.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    @Mappings({
            @Mapping(target="id", source="entity.id"),
            @Mapping(target="name", source="entity.name"),
            @Mapping(target="amount", source="entity.amount"),
            @Mapping(target="unit", source="entity.unit"),
//            @Mapping(target="recipeName", source="recipe.name"),
//            @Mapping(target="recipeId", source="recipe.id"),
    })
    IngredientDTO ingredientToIngredientDTO(Ingredient entity);
    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="name", source="dto.name"),
            @Mapping(target="amount", source="dto.amount"),
            @Mapping(target="unit", source="dto.unit"),
//            @Mapping(target="recipe.id", source="dto.recipeId"),
    })
    Ingredient ingredientDTOToIngredient(IngredientDTO dto);
    List<IngredientDTO> map(Iterable<Ingredient> ingredients);
}
