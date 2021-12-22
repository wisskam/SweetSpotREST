package com.sweetspotrecipies.sweetspot.controller.api;

import com.sweetspotrecipies.sweetspot.api.mapper.RecipeMapper;
import com.sweetspotrecipies.sweetspot.api.model.RecipeDTO;
import com.sweetspotrecipies.sweetspot.api.model.RecipeWithIngredientsDTO;
import com.sweetspotrecipies.sweetspot.model.Recipe;
import com.sweetspotrecipies.sweetspot.service.IngredientService;
import com.sweetspotrecipies.sweetspot.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("${url.prefix.api.v1}/recipe")
public class RestRecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeMapper recipeMapper;
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Iterable<RecipeWithIngredientsDTO>> index() {
        try {
            return new ResponseEntity(
                    recipeMapper.map(
                            recipeService.listAll()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public")
    public ResponseEntity<Iterable<RecipeWithIngredientsDTO>> indexPublic() {
        try {
            return new ResponseEntity(
                    recipeMapper.map(
                            recipeService.listAllPublic()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<RecipeWithIngredientsDTO> getRecipe(@PathVariable("id") Integer id) {
        try {
            Recipe recipeEntity = recipeService.find(id);
            return new ResponseEntity(
                    recipeMapper.recipeToRecipeWithIngredientsDTO(
                            recipeEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeWithIngredientsDTO recipeDTO) {
        try {
            Recipe recipeEntity = recipeMapper.recipeDTOToRecipe(
                    recipeDTO
            );

//            recipeEntity.getIngredients().forEach((ingredient) -> {
//                ingredient.setRecipe(recipeEntity);
//            });

            recipeService.save(recipeEntity);

            recipeEntity.getIngredients().forEach(ingredient -> {
                ingredient.setRecipe(recipeEntity);
                ingredientService.save(ingredient);
            });


            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable("id") Integer id,
                                                @RequestBody RecipeWithIngredientsDTO recipeDTO) {
        try {
            Recipe recipeEntity = recipeService.find(id);
            if (recipeEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            recipeService.save(
                    recipeMapper.recipeDTOToRecipe(
                            recipeDTO
                    )
            );
            return new ResponseEntity(
                    recipeMapper.recipeToRecipeWithIngredientsDTO(
                            recipeEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Integer id) {
        try {
            Recipe recipeEntity = recipeService.find(id);
            if (recipeEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            recipeService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}