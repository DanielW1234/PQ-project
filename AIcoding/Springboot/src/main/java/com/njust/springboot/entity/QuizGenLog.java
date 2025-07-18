package com.njust.springboot.entity;

import java.util.Date;

public class QuizGenLog {
    private Long id;
    private Long lectureId;
    private Date requestTime;
    private String prompt;
    private String response;
    private String status;
    // getter 和 setter 可用 Lombok 简化
} 