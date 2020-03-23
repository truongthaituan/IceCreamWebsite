package com.example.demo.services;

import com.example.demo.models.feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.feedbackRepository;
import java.util.List;
import java.util.Optional;

@Service
public class feedbackServiceImpl implements feedbackService{
    @Autowired
    feedbackRepository feedbackRepository;
    @Override
    public List<feedback> findAll() {
        return (List<feedback>) feedbackRepository.findAll();
    }

    @Override
    public Optional<feedback> getFeedbackById(Long feedback_id) {
        return feedbackRepository.findById(feedback_id);
    }

    @Override
    public void saveOrUpdate(feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long feedback_id) {
        feedbackRepository.deleteById(feedback_id);
    }
}
