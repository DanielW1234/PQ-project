package com.njust.springboot.entity;

import java.util.Date;

public class Quiz {
    private Long id;
    private Long lectureId;
    private Integer fileIndex;
    private String questionText;
    private String correctOption;
    private Date createdAt;
    private Date aiParing;
    // getter 和 setter 可用 Lombok 简化
} 