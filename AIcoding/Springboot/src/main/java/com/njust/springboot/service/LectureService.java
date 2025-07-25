package com.njust.springboot.service;

import com.njust.springboot.entity.Lecture;
import java.util.List;
import java.util.Map;

public interface LectureService {
    int addLecture(Lecture lecture);
    int deleteLecture(Long id);
    int updateLecture(Lecture lecture);
    Lecture getLectureById(Long id);
    List<Lecture> getAllLectures();
    Map<String, Object> getLectureStats(Long id);
} 