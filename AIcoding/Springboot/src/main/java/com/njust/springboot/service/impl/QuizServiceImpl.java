package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Quiz;
import com.njust.springboot.mapper.QuizMapper;
import com.njust.springboot.service.QuizService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.njust.springboot.mapper.AnswerMapper;
import com.njust.springboot.entity.Answer;

@Service
public class QuizServiceImpl implements QuizService {
    @Resource
    private QuizMapper quizMapper;

    @Resource
    private AnswerMapper answerMapper;

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

    @Override
    public List<Quiz> getQuizzesByLectureId(Long lectureId) {
        return quizMapper.selectByLectureId(lectureId);
    }

    @Override
    public Map<String, Object> getQuizStats(Long id) {
        Map<String, Object> stats = new HashMap<>();
        // 查询该题的所有答题记录
        int answerCount = 0;
        int correctCount = 0;
        // 这里假设有AnswerMapper和Answer实体
        // 你可以根据实际情况调整
        List<com.njust.springboot.entity.Answer> allAnswers = answerMapper.selectAll();
        for (com.njust.springboot.entity.Answer ans : allAnswers) {
            if (ans.getQuizId().equals(id)) {
                answerCount++;
                if (Boolean.TRUE.equals(ans.getIsCorrect())) {
                    correctCount++;
                }
            }
        }
        double correctRate = answerCount > 0 ? (correctCount * 100.0 / answerCount) : 0.0;
        stats.put("count", answerCount);
        stats.put("correctRate", correctRate);
        return stats;
    }
} 