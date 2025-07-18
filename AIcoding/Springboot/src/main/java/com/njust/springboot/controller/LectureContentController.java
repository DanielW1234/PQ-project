package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.LectureContent;
import com.njust.springboot.service.LectureContentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectureContent")
public class LectureContentController {
    @Resource
    private LectureContentService lectureContentService;

    @GetMapping
    public Result getAll() {
        return Result.success(lectureContentService.getAllLectureContents());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(lectureContentService.getLectureContentById(id));
    }

    @PostMapping
    public Result add(@RequestBody LectureContent content) {
        lectureContentService.addLectureContent(content);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody LectureContent content) {
        lectureContentService.updateLectureContent(content);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        lectureContentService.deleteLectureContent(id);
        return Result.success();
    }
} 