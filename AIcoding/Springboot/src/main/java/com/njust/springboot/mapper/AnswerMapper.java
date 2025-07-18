package com.njust.springboot.mapper;

import com.njust.springboot.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AnswerMapper {
    int insert(Answer answer);
    int deleteById(Long id);
    int update(Answer answer);
    Answer selectById(Long id);
    List<Answer> selectAll();
} 