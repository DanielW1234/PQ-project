package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Feedback;
import com.njust.springboot.service.FeedbackService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @GetMapping
    public Result getAll() {
        return Result.success(feedbackService.getAllFeedbacks());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(feedbackService.getFeedbackById(id));
    }

    @PostMapping
    public Result add(@RequestBody Feedback feedback) {
        feedbackService.addFeedback(feedback);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Feedback feedback) {
        feedbackService.updateFeedback(feedback);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return Result.success();
    }
} 