package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Feedback;
import com.njust.springboot.mapper.FeedbackMapper;
import com.njust.springboot.service.FeedbackService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public int addFeedback(Feedback feedback) {
        return feedbackMapper.insert(feedback);
    }

    @Override
    public int deleteFeedback(Long id) {
        return feedbackMapper.deleteById(id);
    }

    @Override
    public int updateFeedback(Feedback feedback) {
        return feedbackMapper.update(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackMapper.selectById(id);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> countFeedbackByTypeAndComment(Long lectureId, String feedbackType) {
        return feedbackMapper.countFeedbackByTypeAndComment(lectureId, feedbackType);
    }
} 