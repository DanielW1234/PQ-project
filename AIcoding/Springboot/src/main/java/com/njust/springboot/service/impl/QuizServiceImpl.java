package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Quiz;
import com.njust.springboot.mapper.QuizMapper;
import com.njust.springboot.service.QuizService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Resource
    private QuizMapper quizMapper;

    @Override
    public int addQuiz(Quiz quiz) {
        return quizMapper.insert(quiz);
    }

    @Override
    public int deleteQuiz(Long id) {
        return quizMapper.deleteById(id);
    }

    @Override
    public int updateQuiz(Quiz quiz) {
        return quizMapper.update(quiz);
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizMapper.selectById(id);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizMapper.selectAll();
    }
} 