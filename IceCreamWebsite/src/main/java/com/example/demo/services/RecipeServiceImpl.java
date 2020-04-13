package com.example.demo.services;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.RecipeDTO;
import com.example.demo.models.IceCream;
import com.example.demo.models.Recipe;
import com.example.demo.repositories.IceCreamRepository;
import com.example.demo.repositories.RecipeRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IceCreamRepository iceCreamRepository;
    @Override
    public List<RecipeDTO> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipeDTOS.add(MapperUtil.recipeToDTO(recipe));
        }
        return recipeDTOS;
    }

    @Override
    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id).get();
    }

    @Override
    public RecipeDTO getRecipeById(Long id) {
        return MapperUtil.recipeToDTO(recipeRepository.findById(id).get());
    }

    @Override
    public RecipeDTO insertRecipe(Recipe recipe) {
        return MapperUtil.recipeToDTO(recipeRepository.save(recipe));
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<RecipeDTO> getRecipeByicecreamId(Long id) {
        Optional<IceCream> icecream = iceCreamRepository.findById(id);
        if (!icecream.isPresent()) {
            System.out.println("isEmpty");
        }
        List<Recipe> recipes = icecream.get().getRecipes();
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipeDTOS.add(MapperUtil.recipeToDTO(recipe));
        }
        return recipeDTOS;
    }

}
