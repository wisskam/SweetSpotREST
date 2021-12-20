package com.sweetspotrecipies.sweetspot.service;

import com.sweetspotrecipies.sweetspot.model.Ingredient;
import com.sweetspotrecipies.sweetspot.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Iterable<Ingredient> listAll() {
        return ingredientRepository.findAll();
    }

    public void save(Ingredient ingredient) {
        if(ingredient.getId() == null){
            ingredient.setCreatedAt(new Date());
        }
        else{
            Ingredient existingIngredient = this.find(ingredient.getId());
            ingredient.setCreatedAt(existingIngredient.getCreatedAt());
        }
        ingredientRepository.save(ingredient);
    }

    public Ingredient find(Integer id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        ingredientRepository.deleteById(id);
    }
}