package com.banyuan.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int id;
    private int userId;
    private int blogId;
    private String content;
    private Date time;
    private User user;
}
