package com.example.demo.controllers;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.RecipeDTO;
import com.example.demo.dto.StatusCRUD;
import com.example.demo.models.Recipe;
import com.example.demo.services.RecipeService;
import com.example.demo.services.RecipeService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<RecipeDTO>> findRecipe() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }
    @RequestMapping(value = "/icecream/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDTO>> getRecipeByIceCreamId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(recipeService.getRecipeByicecreamId(id), HttpStatus.OK);
    }
    @RequestMapping(value = "",
            method = RequestMethod.POST)
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody Recipe recipe, UriComponentsBuilder builder) {
       RecipeDTO recipeDTO = recipeService.insertRecipe(recipe);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(recipe.getId()).toUri());
        return new ResponseEntity<>(recipeDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe) {
        Recipe currentRecipe = recipeService.findRecipeById(id);
        currentRecipe.setUser(recipe.getUser());
        currentRecipe.setIcecream(recipe.getIcecream());
        currentRecipe.setTitle(recipe.getTitle());
        currentRecipe.setDescription(recipe.getDescription());
        currentRecipe.setPrice(recipe.getPrice());
        currentRecipe.setStatus(recipe.getStatus());
        currentRecipe.setViewCount(recipe.getViewCount());
        currentRecipe.setImage(recipe.getImage());
        currentRecipe.setDetails(recipe.getDetails());
        currentRecipe.setUploadDate(recipe.getUploadDate());
        return new ResponseEntity<>(MapperUtil.recipeToDTO(recipeService.updateRecipe(currentRecipe)), HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusCRUD> deleteRecipe(@PathVariable("id") Long id) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        recipeService.deleteRecipe(id);
        StatusCRUD statusCRUD = new StatusCRUD(("Delete Recipe Successfully"));
        return new ResponseEntity<>(statusCRUD, HttpStatus.OK);
    }
}
