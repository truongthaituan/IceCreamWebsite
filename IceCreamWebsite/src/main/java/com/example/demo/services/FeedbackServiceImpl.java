package com.example.demo.services;

import com.example.demo.common.MapperUtil;
import com.example.demo.dto.FeedbackDTO;
import com.example.demo.models.Feedback;
import com.example.demo.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Override
    public List<FeedbackDTO> findAll() {
        return MapperUtil.mapList(feedbackRepository.findAll(),FeedbackDTO.class);
    }

    @Override
    public FeedbackDTO getFeedbackById(Long feedback_id) {
        return MapperUtil.mapObject(feedbackRepository.findById(feedback_id).get(),FeedbackDTO.class);
    }

    @Override
    public Feedback findFeedbackById(Long feedback_id) {
        return feedbackRepository.findById(feedback_id).get();
    }

    @Override
    public void saveOrUpdate(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long feedback_id) {
        feedbackRepository.deleteById(feedback_id);
    }
}
