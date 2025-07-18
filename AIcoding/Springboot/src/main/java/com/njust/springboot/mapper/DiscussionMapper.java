package com.njust.springboot.mapper;

import com.njust.springboot.entity.Discussion;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DiscussionMapper {
    int insert(Discussion discussion);
    int deleteById(Long id);
    int update(Discussion discussion);
    Discussion selectById(Long id);
    List<Discussion> selectAll();
} 