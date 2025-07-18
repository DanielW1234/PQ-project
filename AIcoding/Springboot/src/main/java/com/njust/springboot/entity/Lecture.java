package com.njust.springboot.entity;

import java.util.Date;

public class Lecture {
    private Long id;
    private String title;
    private String description;
    private Long organizerId;
    private Long speakerId;
    private Date startTime;
    private Date endTime;
    private Date createdAt;
    private Date updatedAt;
    private String password;
    // getter 和 setter 可用 Lombok 简化
} 