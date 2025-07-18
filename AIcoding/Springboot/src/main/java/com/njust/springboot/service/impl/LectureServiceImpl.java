package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Lecture;
import com.njust.springboot.mapper.LectureMapper;
import com.njust.springboot.service.LectureService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {
    @Resource
    private LectureMapper lectureMapper;

    @Override
    public int addLecture(Lecture lecture) {
        return lectureMapper.insert(lecture);
    }

    @Override
    public int deleteLecture(Long id) {
        return lectureMapper.deleteById(id);
    }

    @Override
    public int updateLecture(Lecture lecture) {
        return lectureMapper.update(lecture);
    }

    @Override
    public Lecture getLectureById(Long id) {
        return lectureMapper.selectById(id);
    }

    @Override
    public List<Lecture> getAllLectures() {
        return lectureMapper.selectAll();
    }
} 