package com.njust.springboot.service;

import com.njust.springboot.entity.LectureContent;
import java.util.List;

public interface LectureContentService {
    int addLectureContent(LectureContent content);
    int deleteLectureContent(Long id);
    int updateLectureContent(LectureContent content);
    LectureContent getLectureContentById(Long id);
    List<LectureContent> getAllLectureContents();
} 