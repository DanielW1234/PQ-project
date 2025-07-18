package com.njust.springboot.entity;

public class Answer {
    private Long id;
    private Long userId;
    private Long quizId;
    private String chosenOption;
    private Boolean isCorrect;
    private Boolean isAnswer;
    // getter 和 setter 可用 Lombok 简化
} 