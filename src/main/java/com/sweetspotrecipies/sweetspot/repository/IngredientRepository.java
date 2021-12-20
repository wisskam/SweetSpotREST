package com.sweetspotrecipies.sweetspot.repository;

import com.sweetspotrecipies.sweetspot.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}

