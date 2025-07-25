package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Lecture;
import com.njust.springboot.mapper.LectureMapper;
import com.njust.springboot.service.LectureService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.njust.springboot.entity.Answer;
import com.njust.springboot.mapper.AnswerMapper;
import com.njust.springboot.entity.Quiz;
import com.njust.springboot.mapper.QuizMapper;

@Service
public class LectureServiceImpl implements LectureService {
    @Resource
    private LectureMapper lectureMapper;
    @Resource
    private AnswerMapper answerMapper;
    @Resource
    private QuizMapper quizMapper;

    @Override
    public int addLecture(Lecture lecture) {
        return lectureMapper.insert(lecture);
    }

    @Override
    public int deleteLecture(Long id) {
        return lectureMapper.deleteById(id);
    }

    @Override
    public int updateLecture(Lecture lecture) {
        return lectureMapper.update(lecture);
    }

    @Override
    public Lecture getLectureById(Long id) {
        return lectureMapper.selectById(id);
    }

    @Override
    public List<Lecture> getAllLectures() {
        return lectureMapper.selectAll();
    }

    @Override
    public Map<String, Object> getLectureStats(Long id) {
        Map<String, Object> stats = new HashMap<>();
        // 查询所有答题记录
        java.util.List<Answer> allAnswers = answerMapper.selectAll();
        int participantCount = 0;
        int answerCount = 0;
        int correctCount = 0;
        java.util.Set<Long> userSet = new java.util.HashSet<>();
        for (Answer ans : allAnswers) {
            // 只统计属于该讲座下的题目
            // 需要先查出该讲座下所有quizId
            // 这里假设有QuizMapper和Quiz实体
            // 你可以根据实际情况调整
        }
        // 先查出该讲座下所有quizId
        java.util.List<Long> quizIds = new java.util.ArrayList<>();
        // 这里假设有QuizMapper
        java.util.List<com.njust.springboot.entity.Quiz> quizzes = quizMapper.selectAll();
        for (com.njust.springboot.entity.Quiz quiz : quizzes) {
            if (quiz.getLectureId().equals(id)) {
                quizIds.add(quiz.getId());
            }
        }
        for (Answer ans : allAnswers) {
            if (quizIds.contains(ans.getQuizId())) {
                answerCount++;
                if (Boolean.TRUE.equals(ans.getIsCorrect())) {
                    correctCount++;
                }
                userSet.add(ans.getUserId());
            }
        }
        participantCount = userSet.size();
        double avgCorrectRate = answerCount > 0 ? (correctCount * 100.0 / answerCount) : 0.0;
        stats.put("participantCount", participantCount);
        stats.put("answerCount", answerCount);
        stats.put("avgCorrectRate", avgCorrectRate);
        return stats;
    }
} 