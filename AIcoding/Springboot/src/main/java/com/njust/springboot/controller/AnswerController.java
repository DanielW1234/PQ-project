package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Answer;
import com.njust.springboot.service.AnswerService;
import com.njust.springboot.entity.Quiz;
import com.njust.springboot.service.QuizService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Resource
    private AnswerService answerService;
    @Resource
    private QuizService quizService;

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

    @PostMapping("/submit")
    public Result submitAnswer(@RequestBody Map<String, Object> map) {
        Long userId = Long.valueOf(map.get("userId").toString());
        Long quizId = Long.valueOf(map.get("quizId").toString());
        String selected = map.get("selected").toString();
        Quiz quiz = quizService.getQuizById(quizId);
        boolean correct = selected.equals(quiz.getCorrectOption());
        // 保存答题记录
        Answer answer = new Answer();
        answer.setUserId(userId);
        answer.setQuizId(quizId);
        answer.setSelectedOption(selected);
        answer.setIsCorrect(correct);
        answerService.addAnswer(answer);
        // 返回是否正确
        Map<String, Object> res = new HashMap<>();
        res.put("correct", correct);
        return Result.success(res);
    }
} 