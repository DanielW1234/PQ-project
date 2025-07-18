package com.njust.springboot.entity;

import java.util.Date;

public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String nickname;
    private Boolean isAnonymous;
    private Date createdAt;
    private Date updatedAt;

    // getter 和 setter 省略，可用 Lombok 注解简化
}
