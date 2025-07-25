package com.njust.springboot.service;

import com.njust.springboot.entity.Quiz;
import java.util.List;
import java.util.Map;

public interface QuizService {
    int addQuiz(Quiz quiz);
    int deleteQuiz(Long id);
    int updateQuiz(Quiz quiz);
    Quiz getQuizById(Long id);
    List<Quiz> getAllQuizzes();
    Map<String, Object> getQuizStats(Long id);
    List<Quiz> getQuizzesByLectureId(Long lectureId);
} 