package com.example.demo.controllers;

import com.example.demo.models.recipe;
import com.example.demo.services.recipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
public class recipeController {
    @Autowired
    recipeService recipeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "recipes", method = RequestMethod.GET)
    public ResponseEntity<List<recipe>> findFag() {
        List<recipe> recipes = recipeService.findAll();
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<recipe> getRecipeyId(@PathVariable("id") Long id) {
        Optional<recipe> recipe = recipeService.getRecipeById(id);
        if (!((Optional) recipe).isPresent()) {
            return new ResponseEntity<>(recipe.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/recipes",
            method = RequestMethod.POST)
    public ResponseEntity<recipe> createRecipe(@RequestBody recipe recipe, UriComponentsBuilder builder) {
        recipeService.saveOrUpdate(recipe);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/recipes/{id}")
                .buildAndExpand(recipe.getId()).toUri());
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<recipe> updateRecipe(@PathVariable("id") Long id, @RequestBody recipe recipe) {
        Optional<recipe> currentRecipe = recipeService.getRecipeById(id);
        if (!currentRecipe.isPresent()) {
            return new ResponseEntity<>(currentRecipe.get(), HttpStatus.NO_CONTENT);
        }

        currentRecipe.get().setUser(recipe.getUser());
        currentRecipe.get().setIcecream(recipe.getIcecream());
        currentRecipe.get().setTitle(recipe.getTitle());
        currentRecipe.get().setDescription(recipe.getDescription());
        currentRecipe.get().setPrice(recipe.getPrice());
        currentRecipe.get().setStatus(recipe.getStatus());
        currentRecipe.get().setView_count(recipe.getView_count());
        currentRecipe.get().setImage(recipe.getImage());
        currentRecipe.get().setDetails(recipe.getDetails());
        currentRecipe.get().setUpload_date(recipe.getUpload_date());

        recipeService.saveOrUpdate(currentRecipe.get());
        return new ResponseEntity<>(currentRecipe.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id) {
        Optional<recipe> recipe = recipeService.getRecipeById(id);
        if (!recipe.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
