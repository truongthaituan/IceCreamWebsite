package com.example.demo.controllers;

import com.example.demo.dto.StatusCRUD;
import com.example.demo.models.Order_details;
import com.example.demo.services.Order_detailService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Order_detailController {
   @Autowired
   Order_detailService order_detailService;
    @RequestMapping(value = "order_details", method = RequestMethod.GET)
    public ResponseEntity<List<Order_details>> findOrder_details() {
        List<Order_details> order_details = order_detailService.findAll();
        if (order_details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order_details, HttpStatus.OK);
    }


    @RequestMapping(value = "/order_details/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order_details> getOrder_DetailById(@PathVariable("id") Long id) {
        Optional<Order_details> order_detail = order_detailService.getOrder_DetailById(id);
        if (!((Optional) order_detail).isPresent()) {
            return new ResponseEntity<>(order_detail.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order_detail.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/order_details/orders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order_details>> getOrder_DetailByOrder(@PathVariable("id") Long orderId) {
        List<Order_details> order_details = order_detailService.findOrderDetailsByOrder(orderId);
        if (order_details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order_details, HttpStatus.OK);
    }
    @RequestMapping(value = "/order_details/recipes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order_details>> getOrder_DetailByRecipe(@PathVariable("id") Long recipeId) {
        List<Order_details> order_details = order_detailService.findOrderDetailsByRecipe(recipeId);
        if (order_details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order_details, HttpStatus.OK);
    }

//    @RequestMapping(value = "/order_details/orders/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Order_details>> deleteOrder_DetailByOrder(@PathVariable("id") Long orderId) {
//        List<Order_details> order_details = order_detailService.deleteOrderDetailsByOrder(orderId);
//        if (order_details.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(order_details, HttpStatus.OK);
//    }

    @RequestMapping(value = "/order_details",
            method = RequestMethod.POST)
    public ResponseEntity<StatusCRUD> createOrder_Detail(@RequestBody Order_details order_details, UriComponentsBuilder builder) {
        order_detailService.saveOrUpdate(order_details);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/order_details/{id}")
//                .buildAndExpand(order_details.ge()).toUri());
        return new ResponseEntity<>(new StatusCRUD("Create Order Details Successfully!"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order_details/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order_details> updateOrder_Detail(@PathVariable("id") Long id, @RequestBody Order_details order_details) {
        Optional<Order_details> currentOrder_detail = order_detailService.getOrder_DetailById(id);
        if (!currentOrder_detail.isPresent()) {
            return new ResponseEntity<>(currentOrder_detail.get(), HttpStatus.NO_CONTENT);
        }
        currentOrder_detail.get().setOrder(order_details.getOrder());
        currentOrder_detail.get().setRecipe(order_details.getRecipe());
        currentOrder_detail.get().setQuantity(order_details.getQuantity());
        currentOrder_detail.get().setPrice(order_details.getPrice());
        order_detailService.saveOrUpdate(currentOrder_detail.get());
        return new ResponseEntity<>(currentOrder_detail.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/order_details/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusCRUD> deleteOrder_Detail(@PathVariable("id") Long id) {
        order_detailService.deleteOrder_Detail(id);
        return new ResponseEntity<>(new StatusCRUD("Delete Successfully"), HttpStatus.OK);
    }
}
