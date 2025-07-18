package com.njust.springboot.entity;

import java.util.Date;

public class LectureContent {
    private Long id;
    private Long lectureId;
    private String contentType;
    private String originalName;
    private String storagePath;
    private String processedText;
    private Date uploadedAt;
    private Date processedAt;
    private Boolean isDownload;
    private Integer fileLength;
    // getter 和 setter 可用 Lombok 简化
} 