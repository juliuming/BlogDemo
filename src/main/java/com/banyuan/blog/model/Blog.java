package com.banyuan.blog.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Blog {
    private Long id;
    private String title;
    private int userId;
    private String content;
    private Date time;
    private int commentNumber;
    private User user;
    private List<Comment> commentList;
}
