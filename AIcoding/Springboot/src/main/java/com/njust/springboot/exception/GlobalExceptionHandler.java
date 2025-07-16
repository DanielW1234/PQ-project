package com.njust.springboot.exception;


import com.njust.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.njust.springboot.controller")
public class GlobalExceptionHandler {//全局异常捕获
    @ExceptionHandler(Exception.class)
    @ResponseBody//标识并返回json
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(CustomException e){
        e.printStackTrace();
        return Result.error(e.getCode(),e.getMsg());
    }
}
