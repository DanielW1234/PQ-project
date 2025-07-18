package com.njust.springboot.mapper;

import com.njust.springboot.entity.QuizGenLog;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QuizGenLogMapper {
    int insert(QuizGenLog log);
    int deleteById(Long id);
    int update(QuizGenLog log);
    QuizGenLog selectById(Long id);
    List<QuizGenLog> selectAll();
} 