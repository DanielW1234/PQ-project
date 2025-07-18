package com.njust.springboot.service;

import com.njust.springboot.entity.Quiz;
import java.util.List;

public interface QuizService {
    int addQuiz(Quiz quiz);
    int deleteQuiz(Long id);
    int updateQuiz(Quiz quiz);
    Quiz getQuizById(Long id);
    List<Quiz> getAllQuizzes();
} 