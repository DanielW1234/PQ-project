package com.njust.springboot.service;

import com.njust.springboot.entity.QuizOption;
import java.util.List;

public interface QuizOptionService {
    int addQuizOption(QuizOption option);
    int deleteQuizOption(Long id);
    int updateQuizOption(QuizOption option);
    QuizOption getQuizOptionById(Long id);
    List<QuizOption> getAllQuizOptions();
    List<QuizOption> getOptionsByQuizId(Long quizId);
} 