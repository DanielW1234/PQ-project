package com.njust.springboot.entity;

import java.util.Date;

public class Feedback {
    private Long id;
    private Long userId;
    private Long lectureId;
    private String feedbackType;
    private String comment;
    private Date createdAt;
    // getter 和 setter 可用 Lombok 简化
} 