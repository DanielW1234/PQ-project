package com.njust.springboot.mapper;

import com.njust.springboot.entity.Quiz;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QuizMapper {
    int insert(Quiz quiz);
    int deleteById(Long id);
    int update(Quiz quiz);
    Quiz selectById(Long id);
    List<Quiz> selectAll();
    List<Quiz> selectByLectureId(Long lectureId);
} 