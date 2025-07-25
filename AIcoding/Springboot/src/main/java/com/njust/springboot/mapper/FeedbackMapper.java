package com.njust.springboot.mapper;

import com.njust.springboot.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FeedbackMapper {
    int insert(Feedback feedback);
    int deleteById(Long id);
    int update(Feedback feedback);
    Feedback selectById(Long id);
    List<Feedback> selectAll();
    List<Map<String, Object>> countFeedbackByTypeAndComment(@Param("lectureId") Long lectureId, @Param("feedbackType") String feedbackType);
} 