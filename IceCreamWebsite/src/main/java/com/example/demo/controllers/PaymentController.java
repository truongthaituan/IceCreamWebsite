package com.example.demo.controllers;

import com.example.demo.models.Payment;
import com.example.demo.services.PaymentService;
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
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @RequestMapping(value = "payments", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> findPayment() {
        List<Payment> payments = paymentService.findAll();
        if (payments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (!((Optional) payment).isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity<>(payment.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/payments",
            method = RequestMethod.POST)
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment, UriComponentsBuilder builder) {
        paymentService.saveOrUpdate(payment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/payments/{id}")
                .buildAndExpand(payment.getPaymentId()).toUri());
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/payments/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") Long id, @RequestBody Payment payment) {
        Optional<Payment> currentPayment = paymentService.getPaymentById(id);
        if (!currentPayment.isPresent()) {
            return new ResponseEntity<>(currentPayment.get(), HttpStatus.NO_CONTENT);
        }
        currentPayment.get().setCardType(payment.getCardType());
        currentPayment.get().setCardNumber(payment.getCardNumber());
        currentPayment.get().setCvv(payment.getCvv());
        currentPayment.get().setNameOnCard(payment.getNameOnCard());
        currentPayment.get().setExpiredDate(payment.getExpiredDate());
        currentPayment.get().setDateOfBirth(payment.getDateOfBirth());
        paymentService.saveOrUpdate(currentPayment.get());
        return new ResponseEntity<>(currentPayment.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePayment(@PathVariable("id") Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (!payment.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        paymentService.deletePayment(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
