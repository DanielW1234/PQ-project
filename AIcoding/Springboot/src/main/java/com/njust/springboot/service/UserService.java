package com.njust.springboot.service;

import com.njust.springboot.entity.User;
import java.util.List;

public interface UserService {
    int addUser(User user);
    int deleteUser(Long id);
    int updateUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
}
