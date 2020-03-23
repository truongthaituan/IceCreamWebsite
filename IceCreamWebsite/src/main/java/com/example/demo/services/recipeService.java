package com.example.demo.services;

import com.example.demo.models.recipe;

import java.util.List;
import java.util.Optional;

public interface recipeService {
    List<recipe> findAll();
    Optional<recipe> getRecipeById(Long id);
    void saveOrUpdate(recipe recipe);
    void deleteRecipe(Long id);
}
