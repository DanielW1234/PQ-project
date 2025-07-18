package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Attendance;
import com.njust.springboot.service.AttendanceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;

    @GetMapping
    public Result getAll() {
        return Result.success(attendanceService.getAllAttendances());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(attendanceService.getAttendanceById(id));
    }

    @PostMapping
    public Result add(@RequestBody Attendance attendance) {
        attendanceService.addAttendance(attendance);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Attendance attendance) {
        attendanceService.updateAttendance(attendance);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return Result.success();
    }
} 