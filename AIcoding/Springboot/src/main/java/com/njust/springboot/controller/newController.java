package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class newController {
    @GetMapping("/hello")
    public Result hello(){
        return Result.success("hello");
    }

    @GetMapping("/count")
    public Result count(){
        return Result.success();
    }

    @GetMapping("/ERR")
    public Result ERR(){
        throw new CustomException("400","禁止请求");
    }

}
