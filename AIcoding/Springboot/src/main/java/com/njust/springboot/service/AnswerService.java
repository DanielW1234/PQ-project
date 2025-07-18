package com.njust.springboot.service;

import com.njust.springboot.entity.Answer;
import java.util.List;

public interface AnswerService {
    int addAnswer(Answer answer);
    int deleteAnswer(Long id);
    int updateAnswer(Answer answer);
    Answer getAnswerById(Long id);
    List<Answer> getAllAnswers();
} 