package com.sweetspotrecipies.sweetspot.repository;

import com.sweetspotrecipies.sweetspot.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}

