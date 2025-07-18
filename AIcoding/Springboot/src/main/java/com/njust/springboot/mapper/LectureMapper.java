package com.njust.springboot.mapper;

import com.njust.springboot.entity.Lecture;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface LectureMapper {
    int insert(Lecture lecture);
    int deleteById(Long id);
    int update(Lecture lecture);
    Lecture selectById(Long id);
    List<Lecture> selectAll();
} 