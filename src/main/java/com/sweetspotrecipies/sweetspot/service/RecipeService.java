package com.sweetspotrecipies.sweetspot.service;

import com.sweetspotrecipies.sweetspot.model.Recipe;
import com.sweetspotrecipies.sweetspot.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> listAll() {
        return recipeRepository.findAll();
    }

    public void save(Recipe recipe) {
        if (recipe.getModifiedAt() == null) {
            recipe.setModifiedAt(new Date());
        }
        if(recipe.getId() == null){
            recipe.setCreatedAt(new Date());
        }
        else{
            Recipe existingRecipe = this.find(recipe.getId());
            recipe.setCreatedAt(existingRecipe.getCreatedAt());
        }
        recipeRepository.save(recipe);
    }

    public Recipe find(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }
}