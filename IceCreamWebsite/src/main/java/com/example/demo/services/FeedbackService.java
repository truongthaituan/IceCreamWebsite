package com.example.demo.services;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.models.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    List<FeedbackDTO> findAll();
    FeedbackDTO getFeedbackById(Long feedback_id);
    Feedback findFeedbackById(Long feedback_id);
    void saveOrUpdate(Feedback feedback);
    void deleteFeedback(Long feedback_id);
}
