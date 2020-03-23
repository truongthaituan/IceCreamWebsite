package com.example.demo.controllers;

import com.example.demo.models.payment;
import com.example.demo.services.paymentService;
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
public class paymentController {
    @Autowired
     paymentService paymentService;
    @RequestMapping(value = "payments", method = RequestMethod.GET)
    public ResponseEntity<List<payment>> findPayment() {
        List<payment> payments = paymentService.findAll();
        if (payments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<payment> getPaymentById(@PathVariable("id") Long id) {
        Optional<payment> payment = paymentService.getPaymentById(id);
        if (!((Optional) payment).isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity<>(payment.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/payments",
            method = RequestMethod.POST)
    public ResponseEntity<payment> createPayment(@RequestBody payment payment, UriComponentsBuilder builder) {
        paymentService.saveOrUpdate(payment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/payments/{id}")
                .buildAndExpand(payment.getPayment_id()).toUri());
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/payments/{id}", method = RequestMethod.PUT)
    public ResponseEntity<payment> updatePayment(@PathVariable("id") Long id, @RequestBody payment payment) {
        Optional<payment> currentPayment = paymentService.getPaymentById(id);
        if (!currentPayment.isPresent()) {
            return new ResponseEntity<>(currentPayment.get(), HttpStatus.NO_CONTENT);
        }
        currentPayment.get().setCard_type(payment.getCard_type());
        currentPayment.get().setCard_number(payment.getCard_number());
        currentPayment.get().setCvv(payment.getCvv());
        currentPayment.get().setName_on_card(payment.getName_on_card());
        currentPayment.get().setExpired_date(payment.getExpired_date());
        currentPayment.get().setDate_of_birth(payment.getDate_of_birth());
        paymentService.saveOrUpdate(currentPayment.get());
        return new ResponseEntity<>(currentPayment.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePayment(@PathVariable("id") Long id) {
        Optional<payment> payment = paymentService.getPaymentById(id);
        if (!payment.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        paymentService.deletePayment(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
