package com.njust.springboot.service;

import com.njust.springboot.entity.Lecture;
import java.util.List;

public interface LectureService {
    int addLecture(Lecture lecture);
    int deleteLecture(Long id);
    int updateLecture(Lecture lecture);
    Lecture getLectureById(Long id);
    List<Lecture> getAllLectures();
} 