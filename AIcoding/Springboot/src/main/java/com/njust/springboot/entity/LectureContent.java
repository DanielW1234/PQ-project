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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getProcessedText() {
        return processedText;
    }

    public void setProcessedText(String processedText) {
        this.processedText = processedText;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Date getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Date processedAt) {
        this.processedAt = processedAt;
    }

    public Boolean getDownload() {
        return isDownload;
    }

    public void setDownload(Boolean download) {
        isDownload = download;
    }

    public Integer getFileLength() {
        return fileLength;
    }

    public void setFileLength(Integer fileLength) {
        this.fileLength = fileLength;
    }
}