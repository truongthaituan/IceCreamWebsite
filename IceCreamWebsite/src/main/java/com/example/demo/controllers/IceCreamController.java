package com.example.demo.controllers;


import com.example.demo.models.IceCream;
import com.example.demo.services.IceCreamService;
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
@Controller
public class IceCreamController {
    @Autowired
    IceCreamService icecreamService;
    @RequestMapping(value = "icecreams", method = RequestMethod.GET)
    public ResponseEntity<List<IceCream>> findIceCream() {
        List<IceCream> icecreams = icecreamService.findAll();
        if (icecreams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(icecreams, HttpStatus.OK);
    }
    @RequestMapping(value = "/icecreams/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IceCream> getIceCreamById(@PathVariable("id") Long id) {
        Optional<IceCream> icecream = icecreamService.getIceCreamById(id);
        if (!((Optional) icecream).isPresent()) {
            return new ResponseEntity<>(icecream.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(icecream.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/icecreams",
            method = RequestMethod.POST)
    public ResponseEntity<IceCream> createIceCream(@RequestBody IceCream icecream, UriComponentsBuilder builder) {
        icecreamService.saveOrUpdate(icecream);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/icecreams/{id}")
                .buildAndExpand(icecream.getId()).toUri());
        return new ResponseEntity<>(icecream, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/icecreams/{id}", method = RequestMethod.PUT)
    public ResponseEntity<IceCream> updateIceCream(@PathVariable("id") Long id, @RequestBody IceCream icecream) {
        Optional<IceCream> currentIceCream = icecreamService.getIceCreamById(id);
        if (!currentIceCream.isPresent()) {
            return new ResponseEntity<>(currentIceCream.get(), HttpStatus.NO_CONTENT);
        }
        currentIceCream.get().setName(icecream.getName());
        currentIceCream.get().setDescription(icecream.getDescription());
        icecreamService.saveOrUpdate(currentIceCream.get());
        return new ResponseEntity<>(currentIceCream.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/icecreams/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteIceCream(@PathVariable("id") Long id) {
        Optional<IceCream> icecream = icecreamService.getIceCreamById(id);
        if (!icecream.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        icecreamService.deleteIceCream(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
