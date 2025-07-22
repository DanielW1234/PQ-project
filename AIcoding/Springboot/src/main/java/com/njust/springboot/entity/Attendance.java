package com.njust.springboot.entity;

import java.util.Date;

public class Attendance {
    private Long id;
    private Long userId;
    private Long lectureId;
    private Date joinedAt;
    private Date leftAt;
    // getter 和 setter 可用 Lombok 简化

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLectureId() {

        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(Date leftAt) {
        this.leftAt = leftAt;
    }
}