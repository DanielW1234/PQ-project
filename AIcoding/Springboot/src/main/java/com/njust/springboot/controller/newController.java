package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class newController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/count")
    public Result count(){
        return Result.success();
    }
}
