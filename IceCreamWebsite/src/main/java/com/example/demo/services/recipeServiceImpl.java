package com.example.demo.services;

import com.example.demo.models.recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.recipeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class recipeServiceImpl implements recipeService{
    @Autowired
    recipeRepository recipeRepository;
    @Override
    public List<recipe> findAll() {
        return (List<recipe>) recipeRepository.findAll();
    }

    @Override
    public Optional<recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
