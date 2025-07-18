package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Lecture;
import com.njust.springboot.service.LectureService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    @Resource
    private LectureService lectureService;

    @GetMapping
    public Result getAll() {
        return Result.success(lectureService.getAllLectures());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(lectureService.getLectureById(id));
    }

    @PostMapping
    public Result add(@RequestBody Lecture lecture) {
        lectureService.addLecture(lecture);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Lecture lecture) {
        lectureService.updateLecture(lecture);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return Result.success();
    }
} 