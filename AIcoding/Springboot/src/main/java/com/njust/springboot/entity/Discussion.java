package com.njust.springboot.entity;

import java.util.Date;

public class Discussion {
    private Long id;
    private Long quizId;
    private Long userId;
    private Long parentId;
    private String content;
    private Date createdAt;
    // getter 和 setter 可用 Lombok 简化
} 