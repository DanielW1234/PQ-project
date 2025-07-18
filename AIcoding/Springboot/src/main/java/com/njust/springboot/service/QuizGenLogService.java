package com.njust.springboot.service;

import com.njust.springboot.entity.QuizGenLog;
import java.util.List;

public interface QuizGenLogService {
    int addQuizGenLog(QuizGenLog log);
    int deleteQuizGenLog(Long id);
    int updateQuizGenLog(QuizGenLog log);
    QuizGenLog getQuizGenLogById(Long id);
    List<QuizGenLog> getAllQuizGenLogs();
} 