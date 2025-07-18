package com.njust.springboot.mapper;

import com.njust.springboot.entity.LectureContent;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface LectureContentMapper {
    int insert(LectureContent content);
    int deleteById(Long id);
    int update(LectureContent content);
    LectureContent selectById(Long id);
    List<LectureContent> selectAll();
} 