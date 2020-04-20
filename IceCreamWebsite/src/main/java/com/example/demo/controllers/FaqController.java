package com.example.demo.controllers;

import com.example.demo.models.Faq;
import com.example.demo.services.FaqService;
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
public class FaqController {
    @Autowired
    FaqService faqService;
    @RequestMapping(value = "faqs", method = RequestMethod.GET)
    public ResponseEntity<List<Faq>> findFag() {
        List<Faq> faqs = faqService.findAll();
        if (faqs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(faqs, HttpStatus.OK);
    }
    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faq> getFaqById(@PathVariable("id") Long id) {
        Optional<Faq> faq = faqService.getFaqById(id);
        if (!((Optional) faq).isPresent()) {
            return new ResponseEntity<>(faq.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(faq.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/faqs",
            method = RequestMethod.POST)
    public ResponseEntity<Faq> createFaq(@RequestBody Faq faq, UriComponentsBuilder builder) {
        faqService.saveOrUpdate(faq);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/faqs/{id}")
                .buildAndExpand(faq.getFaqId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Faq> updateFaq(@PathVariable("id") Long id, @RequestBody Faq faq) {
        Optional<Faq> currentFaq = faqService.getFaqById(id);
        if (!currentFaq.isPresent()) {
            return new ResponseEntity<>(currentFaq.get(), HttpStatus.NO_CONTENT);
        }

        currentFaq.get().setQuestion(faq.getQuestion());
        currentFaq.get().setAnswer(faq.getAnswer());
        currentFaq.get().setStatus(faq.getStatus());
        faqService.saveOrUpdate(currentFaq.get());
        return new ResponseEntity<>(currentFaq.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFaq(@PathVariable("id") Long id) {
        Optional<Faq> faq = faqService.getFaqById(id);
        if (!faq.isPresent()) {
            return new ResponseEntity<>("Empty", HttpStatus.NO_CONTENT);
        }
        faqService.deleteFaq(id);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
}
