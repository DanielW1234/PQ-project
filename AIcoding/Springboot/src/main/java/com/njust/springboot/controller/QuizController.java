package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Quiz;
import com.njust.springboot.service.QuizService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Resource
    private QuizService quizService;

    @GetMapping
    public Result getAll() {
        return Result.success(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(quizService.getQuizById(id));
    }

    @GetMapping("/stats/{id}")
    public Result getQuizStats(@PathVariable Long id) {
        Map<String, Object> stats = quizService.getQuizStats(id);
        return Result.success(stats);
    }

    @PostMapping
    public Result add(@RequestBody Quiz quiz) {
        quizService.addQuiz(quiz);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Quiz quiz) {
        quizService.updateQuiz(quiz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return Result.success();
    }
} 