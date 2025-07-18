package com.njust.springboot.entity;

import java.util.Date;

public class Attendance {
    private Long id;
    private Long userId;
    private Long lectureId;
    private Date joinedAt;
    private Date leftAt;
    // getter 和 setter 可用 Lombok 简化
} 