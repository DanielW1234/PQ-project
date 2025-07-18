package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Answer;
import com.njust.springboot.service.AnswerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Resource
    private AnswerService answerService;

    @GetMapping
    public Result getAll() {
        return Result.success(answerService.getAllAnswers());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(answerService.getAnswerById(id));
    }

    @PostMapping
    public Result add(@RequestBody Answer answer) {
        answerService.addAnswer(answer);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Answer answer) {
        answerService.updateAnswer(answer);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return Result.success();
    }
} 