package com.banyuan.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private String id;
    private String title;
    private int userId;
    private String content;
    Date time;
    private int commentNumber;
}
