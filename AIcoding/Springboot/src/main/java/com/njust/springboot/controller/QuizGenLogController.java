package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.QuizGenLog;
import com.njust.springboot.service.QuizGenLogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizGenLog")
public class QuizGenLogController {
    @Resource
    private QuizGenLogService quizGenLogService;

    @GetMapping
    public Result getAll() {
        return Result.success(quizGenLogService.getAllQuizGenLogs());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(quizGenLogService.getQuizGenLogById(id));
    }

    @PostMapping
    public Result add(@RequestBody QuizGenLog log) {
        quizGenLogService.addQuizGenLog(log);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody QuizGenLog log) {
        quizGenLogService.updateQuizGenLog(log);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        quizGenLogService.deleteQuizGenLog(id);
        return Result.success();
    }
} 