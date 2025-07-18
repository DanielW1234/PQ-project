package com.njust.springboot.service.impl;

import com.njust.springboot.entity.QuizGenLog;
import com.njust.springboot.mapper.QuizGenLogMapper;
import com.njust.springboot.service.QuizGenLogService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class QuizGenLogServiceImpl implements QuizGenLogService {
    @Resource
    private QuizGenLogMapper quizGenLogMapper;

    @Override
    public int addQuizGenLog(QuizGenLog log) {
        return quizGenLogMapper.insert(log);
    }

    @Override
    public int deleteQuizGenLog(Long id) {
        return quizGenLogMapper.deleteById(id);
    }

    @Override
    public int updateQuizGenLog(QuizGenLog log) {
        return quizGenLogMapper.update(log);
    }

    @Override
    public QuizGenLog getQuizGenLogById(Long id) {
        return quizGenLogMapper.selectById(id);
    }

    @Override
    public List<QuizGenLog> getAllQuizGenLogs() {
        return quizGenLogMapper.selectAll();
    }
} 