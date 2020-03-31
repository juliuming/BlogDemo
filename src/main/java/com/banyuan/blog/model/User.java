package com.banyuan.blog.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class User {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String qq;
    private String email;
}
