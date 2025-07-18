package com.njust.springboot.service.impl;

import com.njust.springboot.entity.QuizOption;
import com.njust.springboot.mapper.QuizOptionMapper;
import com.njust.springboot.service.QuizOptionService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class QuizOptionServiceImpl implements QuizOptionService {
    @Resource
    private QuizOptionMapper quizOptionMapper;

    @Override
    public int addQuizOption(QuizOption option) {
        return quizOptionMapper.insert(option);
    }

    @Override
    public int deleteQuizOption(Long id) {
        return quizOptionMapper.deleteById(id);
    }

    @Override
    public int updateQuizOption(QuizOption option) {
        return quizOptionMapper.update(option);
    }

    @Override
    public QuizOption getQuizOptionById(Long id) {
        return quizOptionMapper.selectById(id);
    }

    @Override
    public List<QuizOption> getAllQuizOptions() {
        return quizOptionMapper.selectAll();
    }
} 