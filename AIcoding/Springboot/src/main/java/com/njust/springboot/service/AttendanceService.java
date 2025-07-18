package com.njust.springboot.service;

import com.njust.springboot.entity.Attendance;
import java.util.List;

public interface AttendanceService {
    int addAttendance(Attendance attendance);
    int deleteAttendance(Long id);
    int updateAttendance(Attendance attendance);
    Attendance getAttendanceById(Long id);
    List<Attendance> getAllAttendances();
} 