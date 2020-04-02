package com.banyuan.blog.service;

import com.banyuan.blog.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CommentService{
    List<Comment> showComments(int blogId);
}
