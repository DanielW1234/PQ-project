package com.njust.springboot.mapper;

import com.njust.springboot.entity.QuizOption;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QuizOptionMapper {
    int insert(QuizOption option);
    int deleteById(Long id);
    int update(QuizOption option);
    QuizOption selectById(Long id);
    List<QuizOption> selectAll();
} 