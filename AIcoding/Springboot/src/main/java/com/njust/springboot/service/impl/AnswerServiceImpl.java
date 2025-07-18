package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Answer;
import com.njust.springboot.mapper.AnswerMapper;
import com.njust.springboot.service.AnswerService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswerMapper answerMapper;

    @Override
    public int addAnswer(Answer answer) {
        return answerMapper.insert(answer);
    }

    @Override
    public int deleteAnswer(Long id) {
        return answerMapper.deleteById(id);
    }

    @Override
    public int updateAnswer(Answer answer) {
        return answerMapper.update(answer);
    }

    @Override
    public Answer getAnswerById(Long id) {
        return answerMapper.selectById(id);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerMapper.selectAll();
    }
} 