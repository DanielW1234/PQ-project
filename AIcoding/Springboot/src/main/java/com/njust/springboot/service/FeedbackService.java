package com.njust.springboot.service;

import com.njust.springboot.entity.Feedback;
import java.util.List;
import java.util.Map;

public interface FeedbackService {
    int addFeedback(Feedback feedback);
    int deleteFeedback(Long id);
    int updateFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedbacks();
    List<Map<String, Object>> countFeedbackByTypeAndComment(Long lectureId, String feedbackType);
} 