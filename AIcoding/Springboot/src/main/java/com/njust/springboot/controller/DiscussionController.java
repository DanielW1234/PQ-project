package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Discussion;
import com.njust.springboot.service.DiscussionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    @Resource
    private DiscussionService discussionService;

    @GetMapping
    public Result getAll() {
        return Result.success(discussionService.getAllDiscussions());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(discussionService.getDiscussionById(id));
    }

    @PostMapping
    public Result add(@RequestBody Discussion discussion) {
        discussionService.addDiscussion(discussion);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Discussion discussion) {
        discussionService.updateDiscussion(discussion);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return Result.success();
    }
} 