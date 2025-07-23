package com.njust.springboot.mapper;

import com.njust.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User user);
    int deleteById(Long id);
    int update(User user);
    User selectById(Long id);
    List<User> selectAll();
    User selectByUsername(String username);
}
