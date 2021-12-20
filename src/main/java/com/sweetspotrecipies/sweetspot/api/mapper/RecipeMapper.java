package com.sweetspotrecipies.sweetspot.api.mapper;

import com.sweetspotrecipies.sweetspot.api.model.RecipeDTO;
import com.sweetspotrecipies.sweetspot.api.model.RecipeWithIngredientsDTO;
import com.sweetspotrecipies.sweetspot.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    @Mappings({
            @Mapping(target="id", source="entity.id"),
            @Mapping(target="name", source="entity.name"),
            @Mapping(target="description", source="entity.description"),
            @Mapping(target="steps", source="entity.steps"),
            @Mapping(target="bookName", source="book.name"),
            @Mapping(target="bookId", source="book.id"),
            @Mapping(target="ingredients", source="entity.ingredients"),
    })
    RecipeWithIngredientsDTO recipeToRecipeWithIngredientsDTO(Recipe entity);
    @Mappings({
            @Mapping(target="id", source="entity.id"),
            @Mapping(target="name", source="entity.name"),
            @Mapping(target="description", source="entity.description"),
            @Mapping(target="steps", source="entity.steps"),
            @Mapping(target="bookName", source="book.name"),
            @Mapping(target="bookId", source="book.id"),
    })
    RecipeDTO recipeToRecipeDTO(Recipe entity);
    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="name", source="dto.name"),
            @Mapping(target="description", source="dto.description"),
            @Mapping(target="steps", source="dto.steps"),
            @Mapping(target="imageUrl", source="dto.imageUrl"),
            @Mapping(target="book.id", source="dto.bookId"),
            @Mapping(target="ingredients", source="dto.ingredients"),
    })
    Recipe recipeDTOToRecipe(RecipeWithIngredientsDTO dto);

    List<RecipeDTO> map(Iterable<Recipe> recipes);






}
