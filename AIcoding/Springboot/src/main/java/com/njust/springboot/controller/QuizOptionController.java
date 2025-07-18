package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.QuizOption;
import com.njust.springboot.service.QuizOptionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizOption")
public class QuizOptionController {
    @Resource
    private QuizOptionService quizOptionService;

    @GetMapping
    public Result getAll() {
        return Result.success(quizOptionService.getAllQuizOptions());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(quizOptionService.getQuizOptionById(id));
    }

    @PostMapping
    public Result add(@RequestBody QuizOption option) {
        quizOptionService.addQuizOption(option);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody QuizOption option) {
        quizOptionService.updateQuizOption(option);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        quizOptionService.deleteQuizOption(id);
        return Result.success();
    }
} 