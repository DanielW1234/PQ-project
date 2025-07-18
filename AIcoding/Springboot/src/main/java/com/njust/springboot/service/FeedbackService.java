package com.njust.springboot.service;

import com.njust.springboot.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    int addFeedback(Feedback feedback);
    int deleteFeedback(Long id);
    int updateFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedbacks();
} 