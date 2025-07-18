package com.njust.springboot.service.impl;

import com.njust.springboot.entity.LectureContent;
import com.njust.springboot.mapper.LectureContentMapper;
import com.njust.springboot.service.LectureContentService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class LectureContentServiceImpl implements LectureContentService {
    @Resource
    private LectureContentMapper lectureContentMapper;

    @Override
    public int addLectureContent(LectureContent content) {
        return lectureContentMapper.insert(content);
    }

    @Override
    public int deleteLectureContent(Long id) {
        return lectureContentMapper.deleteById(id);
    }

    @Override
    public int updateLectureContent(LectureContent content) {
        return lectureContentMapper.update(content);
    }

    @Override
    public LectureContent getLectureContentById(Long id) {
        return lectureContentMapper.selectById(id);
    }

    @Override
    public List<LectureContent> getAllLectureContents() {
        return lectureContentMapper.selectAll();
    }
} 