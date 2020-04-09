package com.banyuan.blog.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String nickName;
    private String gender;
    private int age;
    private String address;
    private String qq;
    private String email;
    private List<Blog> blogList;
    private int status;
}
