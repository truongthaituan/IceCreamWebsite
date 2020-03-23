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
//    @RequestMapping(value = "/recipes",
//            method = RequestMethod.POST)
//    public ResponseEntity<faq> createFaq(@RequestBody faq faq, UriComponentsBuilder builder) {
//        faqService.saveOrUpdate(faq);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/faqs/{id}")
//                .buildAndExpand(faq.getFaq_id()).toUri());
//        return new ResponseEntity<>(faq, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<faq> updateFaq(@PathVariable("id") Long id, @RequestBody faq faq) {
//        Optional<faq> currentFaq = faqService.getFaqById(id);
//        if (!currentFaq.isPresent()) {
//            return new ResponseEntity<>(currentFaq.get(), HttpStatus.NO_CONTENT);
//        }
//
//        currentFaq.get().setQuestion(faq.getQuestion());
//        currentFaq.get().setAnswer(faq.getAnswer());
//        currentFaq.get().setStatus(faq.getStatus());
//        faqService.saveOrUpdate(currentFaq.get());
//        return new ResponseEntity<>(currentFaq.get(), HttpStatus.OK);
//    }
//    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> deleteFaq(@PathVariable("id") Long id) {
//        Optional<faq> faq = faqService.getFaqById(id);
//        if (!faq.isPresent()) {
//            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
//        }
//        faqService.deleteFaq(id);
//        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
//    }
}
