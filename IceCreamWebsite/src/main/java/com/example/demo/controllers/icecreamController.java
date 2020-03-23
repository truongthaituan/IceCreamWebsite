package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.demo.models.icecream;
import com.example.demo.services.icecreamService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
public class icecreamController {
    @Autowired
    icecreamService icecreamService;
    @RequestMapping(value = "icecreams", method = RequestMethod.GET)
    public ResponseEntity<List<icecream>> findIceCream() {
        List<icecream> icecreams = icecreamService.findAll();
        if (icecreams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(icecreams, HttpStatus.OK);
    }
    @RequestMapping(value = "/icecreams/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<icecream> getIceCreamById(@PathVariable("id") Long id) {
        Optional<icecream> icecream = icecreamService.getIceCreamById(id);
        if (!((Optional) icecream).isPresent()) {
            return new ResponseEntity<>(icecream.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(icecream.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/icecreams",
            method = RequestMethod.POST)
    public ResponseEntity<icecream> createIceCream(@RequestBody icecream icecream, UriComponentsBuilder builder) {
        icecreamService.saveOrUpdate(icecream);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/icecreams/{id}")
                .buildAndExpand(icecream.getId()).toUri());
        return new ResponseEntity<>(icecream, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/icecreams/{id}", method = RequestMethod.PUT)
    public ResponseEntity<icecream> updateIceCream(@PathVariable("id") Long id, @RequestBody icecream icecream) {
        Optional<icecream> currentIceCream = icecreamService.getIceCreamById(id);
        if (!currentIceCream.isPresent()) {
            return new ResponseEntity<>(currentIceCream.get(), HttpStatus.NO_CONTENT);
        }

        currentIceCream.get().setName(icecream.getName());
        currentIceCream.get().setDescription(icecream.getDescription());
        return new ResponseEntity<>(currentIceCream.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/icecreams/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteIceCream(@PathVariable("id") Long id) {
        Optional<icecream> icecream = icecreamService.getIceCreamById(id);
        if (!icecream.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        icecreamService.deleteIceCream(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
