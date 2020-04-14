package com.example.demo.controllers;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.StatusCRUD;
import com.example.demo.models.Feedback;
import com.example.demo.services.FeedbackService;
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
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @RequestMapping(value = "feedbacks", method = RequestMethod.GET)
    public ResponseEntity<List<FeedbackDTO>> findFeedback() {
        List<FeedbackDTO> feedbacks = feedbackService.findAll();
        if (feedbacks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable("id") Long id) {
        FeedbackDTO feedback = feedbackService.getFeedbackById(id);
        if (feedback == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
    @RequestMapping(value = "/feedbacks",
            method = RequestMethod.POST)
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback, UriComponentsBuilder builder) {
        Feedback feedback1 = new Feedback(feedback.getOrder().getCustomer(),feedback.getOrder(),
                feedback.getDetails(),feedback.getCreateDate());
        feedbackService.saveOrUpdate(feedback1);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/feedbacks/{id}")
                .buildAndExpand(feedback.getFeedbackId()).toUri());
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
        Feedback currentFeedback = feedbackService.findFeedbackById(id);
        if (currentFeedback == null) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
//        currentFeedback.setCustomer(feedback.getCustomer());
        currentFeedback.setOrder(feedback.getOrder());
        currentFeedback.setDetails(feedback.getDetails());
        currentFeedback.setCreateDate(feedback.getCreateDate());
        feedbackService.saveOrUpdate(currentFeedback);
        return new ResponseEntity<>(MapperUtil.mapObject(currentFeedback,FeedbackDTO.class),
                HttpStatus.OK);
    }
    @RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusCRUD> deleteFeedback(@PathVariable("id") Long id) {
        StatusCRUD statusCRUD;
        Feedback feedback = feedbackService.findFeedbackById(id);
        if (feedback == null) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        feedbackService.deleteFeedback(id);
        statusCRUD = new StatusCRUD("Delete Feedback Successfully!");
        return new ResponseEntity<>(statusCRUD, HttpStatus.OK);
    }
}
