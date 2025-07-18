package com.njust.springboot.mapper;

import com.njust.springboot.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FeedbackMapper {
    int insert(Feedback feedback);
    int deleteById(Long id);
    int update(Feedback feedback);
    Feedback selectById(Long id);
    List<Feedback> selectAll();
} 