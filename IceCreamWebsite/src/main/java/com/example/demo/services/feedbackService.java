package com.example.demo.services;

import com.example.demo.models.feedback;

import java.util.List;
import java.util.Optional;

public interface feedbackService {
    List<feedback> findAll();
    Optional<feedback> getFeedbackById(Long feedback_id);
    void saveOrUpdate(feedback feedback);
    void deleteFeedback(Long feedback_id);
}
