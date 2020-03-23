package com.example.demo.controllers;

import com.example.demo.models.feedback;
import com.example.demo.services.feedbackService;
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
public class feedbackController {
    @Autowired
    feedbackService feedbackService;
    @RequestMapping(value = "feedbacks", method = RequestMethod.GET)
    public ResponseEntity<List<feedback>> findFeedback() {
        List<feedback> feedbacks = feedbackService.findAll();
        if (feedbacks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<feedback> getFeedbackById(@PathVariable("id") Long id) {
        Optional<feedback> feedback = feedbackService.getFeedbackById(id);
        if (!((Optional) feedback).isPresent()) {
            return new ResponseEntity<>(feedback.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedback.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/feedbacks",
            method = RequestMethod.POST)
    public ResponseEntity<feedback> createFeedback(@RequestBody feedback feedback, UriComponentsBuilder builder) {
        feedbackService.saveOrUpdate(feedback);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/feedbacks/{id}")
                .buildAndExpand(feedback.getFeedback_id()).toUri());
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<feedback> updateFeedback(@PathVariable("id") Long id, @RequestBody feedback feedback) {
        Optional<feedback> currentFeedback = feedbackService.getFeedbackById(id);
        if (!currentFeedback.isPresent()) {
            return new ResponseEntity<>(currentFeedback.get(), HttpStatus.NO_CONTENT);
        }
        currentFeedback.get().setCustomer(feedback.getCustomer());
        currentFeedback.get().setOrder(feedback.getOrder());
        currentFeedback.get().setDetails(feedback.getDetails());
        currentFeedback.get().setCreate_date(feedback.getCreate_date());
        feedbackService.saveOrUpdate(currentFeedback.get());
        return new ResponseEntity<>(currentFeedback.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") Long id) {
        Optional<feedback> feedback = feedbackService.getFeedbackById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
