package com.example.demo.services;

import com.example.demo.dto.RecipeDTO;
import com.example.demo.models.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<RecipeDTO> findAll();
    Recipe findRecipeById(Long id);
    RecipeDTO getRecipeById(Long id);
    RecipeDTO insertRecipe(Recipe recipe);
    Recipe updateRecipe(Recipe recipe);
    void deleteRecipe(Long id);
    List<RecipeDTO> getRecipeByicecreamId(Long id);
}
