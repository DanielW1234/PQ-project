package com.njust.springboot.service.impl;

import com.njust.springboot.entity.User;
import com.njust.springboot.mapper.UserMapper;
import com.njust.springboot.service.UserService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean register(User user) {
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return false;
        }
        if (user.getIsAnonymous() == null) {
            user.setIsAnonymous(0);
        }
        if (user.getRole() == null) {
            user.setRole("user");
        }
        Date now = new Date();
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(now);
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(now);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user) > 0;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
} 