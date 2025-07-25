package com.njust.springboot.controller;
import com.njust.springboot.common.Result;
import com.njust.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.njust.springboot.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public Result getAll() {
        return Result.success(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(userService.getUserById(id));
    }

    @PostMapping
    public Result add(@RequestBody User user) {
        userService.addUser(user);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        boolean success = userService.register(user);
        return success ? Result.success() : Result.error("400", "用户名已存在");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        User user = userService.login(username, password);
        return user != null ? Result.success(user) : Result.error("400", "用户名或密码错误");
    }
}
