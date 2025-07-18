package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Attendance;
import com.njust.springboot.mapper.AttendanceMapper;
import com.njust.springboot.service.AttendanceService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;

    @Override
    public int addAttendance(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }

    @Override
    public int deleteAttendance(Long id) {
        return attendanceMapper.deleteById(id);
    }

    @Override
    public int updateAttendance(Attendance attendance) {
        return attendanceMapper.update(attendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceMapper.selectById(id);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceMapper.selectAll();
    }
} 