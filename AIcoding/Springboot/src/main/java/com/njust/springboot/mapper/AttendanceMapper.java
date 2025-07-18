package com.njust.springboot.mapper;

import com.njust.springboot.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AttendanceMapper {
    int insert(Attendance attendance);
    int deleteById(Long id);
    int update(Attendance attendance);
    Attendance selectById(Long id);
    List<Attendance> selectAll();
} 