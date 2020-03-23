package com.example.demo.controllers;

import com.example.demo.models.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.services.customerService;
import java.util.List;
import java.util.Optional;

@Controller
public class customerController {
    @Autowired
    customerService customerService;
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public ResponseEntity<List<customer>> findCustomer() {
        List<customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<customer> getCustomerById(@PathVariable("id") Long id) {
        Optional<customer> customer = customerService.getCustomerById(id);
        if (!((Optional) customer).isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/customers",
            method = RequestMethod.POST)
    public ResponseEntity<customer> createCustomer(@RequestBody customer customer, UriComponentsBuilder builder) {
        customerService.saveOrUpdate(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customers/{id}")
                .buildAndExpand(customer.getCustomer_id()).toUri());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<customer> updateCustomer(@PathVariable("id") Long id, @RequestBody customer customer) {
        Optional<customer> currentCustomer = customerService.getCustomerById(id);
        if (!currentCustomer.isPresent()) {
            return new ResponseEntity<>(currentCustomer.get(), HttpStatus.NO_CONTENT);
        }
        currentCustomer.get().setName(customer.getName());
        currentCustomer.get().setEmail(customer.getEmail());
        currentCustomer.get().setPhone(customer.getPhone());
        currentCustomer.get().setPassword(customer.getPassword());
        currentCustomer.get().setDate_of_birth(customer.getDate_of_birth());
        currentCustomer.get().setAddress(customer.getAddress());
        currentCustomer.get().setGender(customer.getGender());
        currentCustomer.get().setAvatar(customer.getAvatar());
        currentCustomer.get().setStatus(customer.getStatus());
        currentCustomer.get().setNumber_of_login_failed(customer.getNumber_of_login_failed());
        customerService.saveOrUpdate(currentCustomer.get());
        return new ResponseEntity<>(currentCustomer.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFaq(@PathVariable("id") Long id) {
        Optional<customer> customer = customerService.getCustomerById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
