package com.example.demo.controllers;

import com.example.demo.models.order_details;
import com.example.demo.services.order_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
@Controller
public class order_detailController {
   @Autowired
order_detailService order_detailService;
    @RequestMapping(value = "order_details", method = RequestMethod.GET)
    public ResponseEntity<List<order_details>> findOrder_details() {
        List<order_details> order_details = order_detailService.findAll();
        if (order_details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order_details, HttpStatus.OK);
    }
    @RequestMapping(value = "/order_details/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<order_details> getOrder_DetailById(@PathVariable("id") Long id) {
        Optional<order_details> order_detail = order_detailService.getOrder_DetailById(id);
        if (!((Optional) order_detail).isPresent()) {
            return new ResponseEntity<>(order_detail.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order_detail.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/order_details",
            method = RequestMethod.POST)
    public ResponseEntity<String> createOrder_Detail(@RequestBody order_details order_details, UriComponentsBuilder builder) {
        order_detailService.saveOrUpdate(order_details);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/order_details/{id}")
//                .buildAndExpand(order_details.ge()).toUri());
        return new ResponseEntity<>("create", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order_details/{id}", method = RequestMethod.PUT)
    public ResponseEntity<order_details> updateOrder_Detail(@PathVariable("id") Long id, @RequestBody order_details order_details) {
        Optional<order_details> currentOrder_detail = order_detailService.getOrder_DetailById(id);
        if (!currentOrder_detail.isPresent()) {
            return new ResponseEntity<>(currentOrder_detail.get(), HttpStatus.NO_CONTENT);
        }
        currentOrder_detail.get().setOrder(order_details.getOrder());
        currentOrder_detail.get().setRecipe(order_details.getRecipe());
        currentOrder_detail.get().setQuantity(order_details.getQuantity());
        currentOrder_detail.get().setPrice(order_details.getPrice());
        currentOrder_detail.get().setNotes(order_details.getNotes());
        order_detailService.saveOrUpdate(currentOrder_detail.get());
        return new ResponseEntity<>(currentOrder_detail.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/order_details/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrder_Detail(@PathVariable("id") Long id) {
        Optional<order_details> order_details = order_detailService.getOrder_DetailById(id);
        if (!order_details.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        order_detailService.deleteOrder_Detail(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
