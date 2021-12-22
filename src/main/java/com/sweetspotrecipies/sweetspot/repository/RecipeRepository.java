package com.sweetspotrecipies.sweetspot.repository;

import com.sweetspotrecipies.sweetspot.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findByPublished(Boolean published);
}

